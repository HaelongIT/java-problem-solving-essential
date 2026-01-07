# 🚀 실무 비즈니스 로직 통합 연습 가이드

이 프로젝트는 현업에서 가장 많이 쓰이는 **문자열 처리, 해시 활용, 비즈니스 로직 구현** 능력을 한 번에 기르기 위해 설계되었습니다.

## 📂 연습용 아키텍처

실무와 동일하게 **계층형 구조(Layered Architecture)**로 구성되어 있습니다.

### 1. 데이터 흐름도 (Request Flow)
```text
[Client: PracticeRunner] 
      │
      ▼
[Controller: OrderController] --- (Parser 호출) ──▶ [Service: OrderParser] (String 파싱)
      │                                                   │
      ▼                                                   ▼
[Service: OrderService] (Implementation: 비즈니스 로직) ◀───┘
      │
      ▼
[Repository: InMemoryRepository] (Hash: 데이터 조회)
      │
      ▼
[Model: User, Product, ParsedOrder] (Data)
```

### 2. 프로젝트 폴더 구조 (Directory Structure)
```text
practice.order_system
├── controller
│   └── OrderController.java    # API 진입점, 예외 처리 응답
├── service
│   ├── OrderParser.java        # 미션 1: 문자열 파싱
│   └── OrderService.java       # 미션 3: 비즈니스 로직 구현
├── repository
│   └── InMemoryRepository.java # 미션 2: HashMap 활용 데이터 검색
├── model
│   ├── User.java               # 사용자 정보 (Entity)
│   └── Product.java            # 상품 정보 (Entity)
└── PracticeRunner.java         # 전체 실행 및 테스트
```

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
- **미션**: 다음의 복잡한 실무 규칙을 코드로 녹여내고 **최종 금액을 리턴**하세요.
    - 유효한 사용자인가? (존재 여부, 활성 상태 확인)
    - 회원 등급별 할인율 적용 (GOLD 20%, SILVER 10%)
    - 추가 쿠폰(SALE10) 중복 할인 계산 (할인된 금액에서 다시 할인)
- **포인트**: 예외 상황(Exception)을 고려하고, 조건문(`if-else`)을 지저분하지 않게 배치하는 연습을 합니다. `PracticeValidator`가 이 리턴값을 검증합니다.

### 4단계: 시스템 가동 (End-to-End)
- **대상 파일**: `PracticeRunner.java`
- **미션**: `main` 함수 안에서 `orderController`를 호출하는 주석을 해제하고 시스템을 실행하세요.
- **확인**: 콘솔 창에 각 주문 로그별로 `200 OK` 또는 `400 BAD REQUEST` 결과가 의도대로 찍히는지 확인합니다.

---

## ✅ 자동 검증기 사용법 (PracticeValidator)
내가 짠 코드가 정답인지 일일이 확인하기 번거롭다면 **`PracticeValidator`**를 실행하세요.

1.  **`practice.order_system.PracticeValidator`** 클래스를 실행합니다.
2.  콘솔창에 **✅ PASS**가 뜨는지 확인합니다.
3.  만약 **❌ FAIL**이 뜬다면, 해당 미션의 요구사항에 맞게 다시 구현해 보세요.

---

## 💡 실무력을 높이는 추가 팁
1. **예외 처리**: 단순히 에러를 내지 말고, "왜 에러가 났는지" 상세한 메시지를 `throw new IllegalArgumentException("메시지")`로 던져보세요.
2. **코드 가독성**: `calculateDiscount()` 처럼 기능을 별도 메서드로 추출하여 `processOrder`의 전체 흐름이 한눈에 읽히도록 코드를 짜보세요.
3. **확장**: 성능이 중요하다면 어디에 캐시(Hash)를 더 적용할 수 있을지 고민해 보세요.
