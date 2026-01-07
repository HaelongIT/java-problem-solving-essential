# 🚀 실무 비즈니스 로직 통합 연습 가이드

이 프로젝트는 현업에서 가장 많이 쓰이는 **문자열 처리, 해시 활용, 비즈니스 로직 구현** 능력을 한 번에 기르기 위해 설계되었습니다.

## 📂 연습용 아키텍처
실무와 동일하게 **계층형 구조(Layered Architecture)**로 구성되어 있습니다.
- `controller`: 요청을 받고 응답을 반환하는 관문 (API 흉내)
- `service`: 핵심 비즈니스 로직 및 데이터 가공 (요리사 역할)
- `repository`: 데이터를 저장하고 조회하는 계층 (DB 흉내)
- `model`: 시스템에서 사용하는 데이터 객체 (DTO/Entity)

---

## 🛠️ 단계별 연습 순서 (Mission)

모든 코드에는 **`// TODO`** 주석이 달려 있습니다. 아래 순서대로 빈칸을 채우며 시스템을 완성하세요.

### 1단계: 문자열 파싱 (String Practice)
- **대상 파일**: `service/OrderParser.java`
- **미션**: `"ORD001|user1|PROD001:2,PROD002:1|SALE10"` 형태의 원시 로그를 분리하여 `ParsedOrder` 객체에 담으세요.
- **포인트**: `split()`, `substring()`, `Integer.parseInt()` 등을 활용하여 지저분한 데이터를 정제합니다.

### 2단계: 데이터 조회 최적화 (Hash Practice)
- **대상 파일**: `repository/InMemoryRepository.java`
- **미션**: `HashMap`을 사용하여 사용자(User)와 상품(Product) 정보를 ID값으로 즉시 찾을 수 있게 구현하세요.
- **포인트**: `Map.get()`을 사용하여 반복문 없이 데이터를 가져오는 '해시 탐색' 감각을 익힙니다.

### 3단계: 비즈니스 규칙 적용 (Implementation Practice)
- **대상 파일**: `service/OrderService.java`
- **미션**: 다음의 복잡한 실무 규칙을 코드로 녹여내세요.
    - 유효한 사용자인가? (존재 여부, 활성 상태 확인)
    - 회원 등급별 할인율 적용 (GOLD 20%, SILVER 10%)
    - 추가 쿠폰(SALE10) 중복 할인 계산
- **포인트**: 예외 상황(Exception)을 고려하고, 조건문(`if-else`)을 지저분하지 않게 배치하는 연습을 합니다.

### 4단계: 시스템 가동 (End-to-End)
- **대상 파일**: `PracticeRunner.java`
- **미션**: `main` 함수 안에서 `orderController`를 호출하는 주석을 해제하고 시스템을 실행하세요.
- **확인**: 콘솔 창에 각 주문 로그별로 `200 OK` 또는 `400 BAD REQUEST` 결과가 의도대로 찍히는지 확인합니다.

---

## 💡 실무력을 높이는 추가 팁
1. **예외 처리**: 단순히 에러를 내지 말고, "왜 에러가 났는지" 상세한 메시지를 `throw new IllegalArgumentException("메시지")`로 던져보세요.
2. **코드 가독성**: `calculateDiscount()` 처럼 기능을 별도 메서드로 추출하여 `processOrder`의 전체 흐름이 한눈에 읽히도록 코드를 짜보세요.
3. **확장**: 성능이 중요하다면 어디에 캐시(Hash)를 더 적용할 수 있을지 고민해 보세요.
