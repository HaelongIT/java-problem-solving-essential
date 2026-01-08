package practice.order_system;

import practice.order_system.mapper.OrderMapper;
import practice.order_system.model.Product;
import practice.order_system.model.User;
import practice.order_system.service.OrderParser;
import practice.order_system.service.OrderService;

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
            String testLog = "ORD999|user1|PROD001:2,PROD002:1|SALE10";
            OrderParser.ParsedOrder res = parser.parse(testLog);

            if (res != null &&
                    "ORD999".equals(res.orderId) &&
                    "user1".equals(res.userId) &&
                    res.items.size() == 2 &&
                    "PROD001".equals(res.items.get(0).productId) &&
                    res.items.get(0).quantity == 2 &&
                    "PROD002".equals(res.items.get(1).productId) &&
                    res.items.get(1).quantity == 1 &&
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
            OrderMapper mapper = new OrderMapper();
            mapper.insertUser(new User("testUser", "테스터", "GOLD", true));
            mapper.insertProduct(new Product("testProd", "테스트상품", "카테고리", 1000));

            User user = mapper.selectUserById("testUser");
            Product prod = mapper.selectProductById("testProd");

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
            OrderMapper mapper = new OrderMapper();
            mapper.insertUser(new User("goldUser", "부자", "GOLD", true));
            mapper.insertUser(new User("inactiveUser", "비활성", "GOLD", false));
            mapper.insertProduct(new Product("PROD", "비싼거", "전자", 100000));

            OrderService service = new OrderService(mapper);

            // 테스트 1: 정상 케이스 (GOLD + SALE10)
            OrderParser.ParsedOrder order1 = createTestOrder("goldUser", "PROD", 1, "SALE10");
            int price = service.processOrder(order1);
            if (price != 72000) {
                System.out.println("❌ FAIL (계산된 금액(" + price + ")이 기대값(72000)과 다름)");
                return;
            }

            // 테스트 2: 존재하지 않는 사용자
            OrderParser.ParsedOrder order2 = createTestOrder("unknown", "PROD", 1, "NONE");
            try {
                service.processOrder(order2);
                System.out.println("❌ FAIL (존재하지 않는 사용자 예외 처리 안 됨)");
                return;
            } catch (IllegalArgumentException e) {
                // 정상: 예외가 발생해야 함
            }

            // 테스트 3: 비활성 사용자
            OrderParser.ParsedOrder order3 = createTestOrder("inactiveUser", "PROD", 1, "NONE");
            try {
                service.processOrder(order3);
                System.out.println("❌ FAIL (비활성 사용자 예외 처리 안 됨)");
                return;
            } catch (IllegalArgumentException e) {
                // 정상: 예외가 발생해야 함
            }

            // 테스트 4: 존재하지 않는 상품
            OrderParser.ParsedOrder order4 = createTestOrder("goldUser", "INVALID", 1, "NONE");
            try {
                service.processOrder(order4);
                System.out.println("❌ FAIL (존재하지 않는 상품 예외 처리 안 됨)");
                return;
            } catch (IllegalArgumentException e) {
                // 정상: 예외가 발생해야 함
            }

            System.out.println("✅ PASS");
        } catch (Exception e) {
            System.out.println("❌ FAIL (예상치 못한 에러: " + e.getMessage() + ")");
        }
    }

    // 테스트용 주문 객체 생성 헬퍼 메서드
    private static OrderParser.ParsedOrder createTestOrder(String userId, String productId, int quantity, String couponCode) {
        OrderParser.ParsedOrder order = new OrderParser.ParsedOrder();
        order.userId = userId;
        order.couponCode = couponCode;
        OrderParser.ParsedOrder.Item item = new OrderParser.ParsedOrder.Item();
        item.productId = productId;
        item.quantity = quantity;
        order.items.add(item);
        return order;
    }
}
