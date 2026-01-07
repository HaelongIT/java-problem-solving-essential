package practice.order_system.service;

import java.util.ArrayList;
import java.util.List;

/**
 * [문자열 연습부]
 * 로그 문자열을 파싱하는 서비스입니다.
 */
public class OrderParser {

    /**
     * 로그 형식: "주문번호|사용자ID|상품ID:수량,상품ID:수량|쿠폰코드"
     * 예시: "ORD001|userA|PROD001:2,PROD002:1|SALE10"
     *
     * TODO: 위 형식의 문자열을 파싱하여 필요한 데이터를 추출하세요.
     * split(), substring() 등 자바 내장 함수를 적극 활용해보세요.
     */
    public ParsedOrder parse(String log) {
        // 1. | 구분자로 분리
        // 2. 상품 목록(PROD001:2,...)은 다시 분리하여 처리
        // 3. ParsedOrder 객체에 담아 리턴
        
        // 여기에 로직 작성 (문자열 연습)
        return null; // 완성해보세요
    }

    public static class ParsedOrder {
        public String orderId;
        public String userId;
        public List<Item> items = new ArrayList<>();
        public String couponCode;

        public static class Item {
            public String productId;
            public int quantity;
        }
    }
}
