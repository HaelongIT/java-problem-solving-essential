package practice.order_system;

import practice.order_system.controller.OrderController;
import practice.order_system.model.Product;
import practice.order_system.model.User;
import practice.order_system.repository.InMemoryRepository;
import practice.order_system.service.OrderParser;
import practice.order_system.service.OrderService;

/**
 * 전체 시스템 실행기
 */
public class PracticeRunner {
    public static void main(String[] args) {
        InMemoryRepository repository = new InMemoryRepository();

        // 1. 기초 데이터 준비
        setupData(repository);

        OrderParser parser = new OrderParser();
        OrderService orderService = new OrderService(repository);

        // 2. 컨트롤러 생성 (실무 진입점)
        OrderController orderController = new OrderController(orderService, parser);

        // 3. 테스트용 지저분한 주문 로그들
        String[] logs = {
                "ORD001|user1|PROD001:2,PROD002:1|SALE10", // 정상 (GOLD, 쿠폰)
                "ORD002|user2|PROD003:5|NONE", // 정상 (SILVER, 쿠폰없음)
                "ORD003|user3|PROD001:1|NONE", // 에러 (비활성 사용자)
                "ORD004|unknown|PROD002:1|NONE", // 에러 (존재하지 않는 유저)
                "ORD005|user1|INVALID:1|NONE" // 에러 (존재하지 않는 상품)
        };

        System.out.println("=== 주문 처리 시스템 가동 ===");

        for (String log : logs) {
            System.out.println("\n[클라이언트 요청]: " + log);
            // TODO: 컨트롤러를 호출하여 실무와 동일한 흐름을 시나리오대로 완성하세요.
            // String response = orderController.postOrder(log);
            // System.out.println("[서버 응답]: " + response);
        }
    }

    private static void setupData(InMemoryRepository repository) {
        repository.saveUser(new User("user1", "김철수", "GOLD", true));
        repository.saveUser(new User("user2", "이영희", "SILVER", true));
        repository.saveUser(new User("user3", "박한결", "BRONZE", false));

        repository.saveProduct(new Product("PROD001", "키보드", "전자제품", 50000));
        repository.saveProduct(new Product("PROD002", "마우스", "전자제품", 30000));
        repository.saveProduct(new Product("PROD003", "노트", "문구", 2000));
    }
}
