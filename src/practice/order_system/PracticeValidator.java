package practice.order_system;

import practice.order_system.model.Product;
import practice.order_system.model.User;
import practice.order_system.repository.InMemoryRepository;
import practice.order_system.service.OrderParser;
import practice.order_system.service.OrderService;

import java.util.List;

/**
 * [자동 검증기]
 * 각 미션의 구현 결과가 정답과 일치하는지 자동으로 체크합니다.
 */
public class PracticeValidator {

    public static void main(String[] args) {
        System.out.println("========= 자동 검증 시스템 시작 =========");

        validateMission1(); // 문자열 파싱 검증
        validateMission2(); // 해시 기반 조회 검증
        validateMission3(); // 비즈니스 로직 검증

        System.out.println("\n========================================");
    }

    private static void validateMission1() {
        System.out.print("[Mission 1: 문자열 파싱] -> ");
        try {
            OrderParser parser = new OrderParser();
            String testLog = "ORD999|user1|PROD001:2|SALE10";
            OrderParser.ParsedOrder res = parser.parse(testLog);

            if (res != null &&
                    "ORD999".equals(res.orderId) &&
                    "user1".equals(res.userId) &&
                    res.items.size() == 1 &&
                    "PROD001".equals(res.items.get(0).productId) &&
                    res.items.get(0).quantity == 2 &&
                    "SALE10".equals(res.couponCode)) {
                System.out.println("✅ PASS");
            } else {
                System.out.println("❌ FAIL (데이터가 일치하지 않음)");
            }
        } catch (Exception e) {
            System.out.println("❌ FAIL (에러 발생: " + e.getMessage() + ")");
        }
    }

    private static void validateMission2() {
        System.out.print("[Mission 2: 해시 조회] -> ");
        try {
            InMemoryRepository repo = new InMemoryRepository();
            repo.saveUser(new User("testUser", "테스터", "GOLD", true));
            repo.saveProduct(new Product("testProd", "테스트상품", "카테고리", 1000));

            User user = repo.findUserById("testUser");
            Product prod = repo.findProductById("testProd");

            if (user != null && "테스터".equals(user.getName()) &&
                    prod != null && "테스트상품".equals(prod.getName())) {
                System.out.println("✅ PASS");
            } else {
                System.out.println("❌ FAIL (조회 결과가 없거나 틀림)");
            }
        } catch (Exception e) {
            System.out.println("❌ FAIL (에러 발생: " + e.getMessage() + ")");
        }
    }

    private static void validateMission3() {
        System.out.print("[Mission 3: 비즈니스 로직] -> ");
        try {
            InMemoryRepository repo = new InMemoryRepository();
            repo.saveUser(new User("goldUser", "부자", "GOLD", true));
            repo.saveProduct(new Product("PROD", "비싼거", "전자", 100000));

            OrderParser.ParsedOrder order = new OrderParser.ParsedOrder();
            order.userId = "goldUser";
            order.couponCode = "SALE10";
            OrderParser.ParsedOrder.Item item = new OrderParser.ParsedOrder.Item();
            item.productId = "PROD";
            item.quantity = 1;
            order.items.add(item);

            OrderService service = new OrderService(repo);
            int price = service.processOrder(order);

            // 기대 금액: 100,000 * 0.8(GOLD) * 0.9(SALE10) = 72,000
            if (price == 72000) {
                System.out.println("✅ PASS");
            } else {
                System.out.println("❌ FAIL (계산된 금액(" + price + ")이 기대값(72000)과 다름)");
            }
        } catch (Exception e) {
            System.out.println("❌ FAIL (에러 발생: " + e.getMessage() + ")");
        }
    }
}
