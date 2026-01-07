package practice.order_system.controller;

import practice.order_system.service.OrderParser;
import practice.order_system.service.OrderService;

/**
 * [컨트롤러 계층]
 * 외부 요청을 받아 서비스를 호출하고 결과를 반환합니다.
 * 실무에서는 HTTP 요청(JSON, 텍스트 등)을 받는 관문 역할을 합니다.
 */
public class OrderController {
    private final OrderService orderService;
    private final OrderParser orderParser;

    public OrderController(OrderService orderService, OrderParser orderParser) {
        this.orderService = orderService;
        this.orderParser = orderParser;
    }

    /**
     * 주문 요청 처리 API (흉내)
     * 
     * @param log raw 문자열 로그
     * @return 처리 결과 메시지 (실무라면 ResponseEntity 등을 반환)
     */
    public String postOrder(String log) {
        try {
            // 1. 문자열 파싱 (String 연습부 호출)
            OrderParser.ParsedOrder parsedOrder = orderParser.parse(log);

            if (parsedOrder == null) {
                return "400 BAD REQUEST: 파싱 결과가 없습니다.";
            }

            // 2. 서비스 로직 실행 (구현/해시 연습부 호출)
            orderService.processOrder(parsedOrder);

            return "200 OK: 주문이 성공적으로 처리되었습니다.";
        } catch (IllegalArgumentException e) {
            return "400 BAD REQUEST: " + e.getMessage();
        } catch (Exception e) {
            return "500 INTERNAL SERVER ERROR: " + e.getMessage();
        }
    }
}
