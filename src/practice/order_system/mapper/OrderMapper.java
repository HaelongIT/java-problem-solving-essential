package practice.order_system.mapper;

import practice.order_system.model.Product;
import practice.order_system.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * [MyBatis Mapper 흉내]
 * 실무에서는 인터페이스+XML이지만, 여기서는 연습을 위해
 * 클래스 내부에 HashMap을 두고 SQL 조회를 흉내 냅니다.
 * (Repository 계층 없이 바로 사용)
 */
public class OrderMapper {
    private Map<String, User> userMap = new HashMap<>();
    private Map<String, Product> productMap = new HashMap<>();

    /**
     * 사용자 데이터 삽입 (MyBatis INSERT 역할)
     */
    public void insertUser(User user) {
        userMap.put(user.getId(), user);
    }

    /**
     * 상품 데이터 삽입 (MyBatis INSERT 역할)
     */
    public void insertProduct(Product product) {
        productMap.put(product.getId(), product);
    }

    /**
     * TODO: 사용자 ID로 정보를 조회하는 쿼리를 대신합니다. (Hash 연습)
     */
    public User selectUserById(String id) {
        // 여기에 로직 작성
        return null;
    }

    /**
     * TODO: 상품 ID로 정보를 조회하는 쿼리를 대신합니다. (Hash 연습)
     */
    public Product selectProductById(String id) {
        // 여기에 로직 작성
        return null;
    }
}
