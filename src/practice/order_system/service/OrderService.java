package practice.order_system.service;

import practice.order_system.model.Product;
import practice.order_system.model.User;
import practice.order_system.repository.InMemoryRepository;

import java.util.Map;

/**
 * [구현 & 비즈니스 로직 연습부]
 * 실제 비즈니스 규칙을 적용하는 서비스입니다.
 */
public class OrderService {
    private final InMemoryRepository repository;

    public OrderService(InMemoryRepository repository) {
        this.repository = repository;
    }

    /**
     * TODO: 다음 비즈니스 규칙을 만족하는 주문 처리 로직을 구현하세요.
     *
     * 1. 유입된 사용자 ID가 시스템에 존재해야 함 (repository 활용)
     * 2. 사용자는 활성화 상태(isActive)여야 함
     * 3. 주문에 포함된 모든 상품 ID가 시스템에 존재해야 함
     * 4. 최종 가격 계산:
     * - 기본: 상품 가격 * 수량
     * - 할인 규칙:
     * - GOLD 회원: 총액에서 20% 할인
     * - SILVER 회원: 총액에서 10% 할인
     * - BRONZE 회원: 할인 없음
     * - 쿠폰(SALE10)이 있다면 추가로 10% 할인 (등급 할인 후 중복 적용 가능)
     *
     * 5. 규칙 위반 시 적절한 예외 메시지를 담은 RuntimeException을 던지세요.
     * 
     * @return 최종 계산된 주문 금액
     */
    public int processOrder(OrderParser.ParsedOrder parsedOrder) {
        // 여기에 로직 작성 (구현 연습)
        // 1. 검증 (Validation)
        // 2. 가격 계산 (Calculation)
        // 3. 결과 출력 (Result)
        return 0; // TODO: 실제 계산된 금액을 리턴하세요
    }
}
