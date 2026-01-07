package practice.order_system.repository;

import practice.order_system.model.Product;
import practice.order_system.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * [해시 연습부]
 * DB 대신 HashMap을 사용하여 데이터를 관리합니다.
 */
public class InMemoryRepository {
    private Map<String, User> userMap = new HashMap<>();
    private Map<String, Product> productMap = new HashMap<>();

    public void saveUser(User user) {
        userMap.put(user.getId(), user);
    }

    public void saveProduct(Product product) {
        productMap.put(product.getId(), product);
    }

    /**
     * TODO: HashMap의 장점을 살려 ID로 사용자를 O(1)로 찾으세요.
     */
    public User findUserById(String id) {
        // 여기에 로직 작성 (Hash 연습)
        return null;
    }

    /**
     * TODO: HashMap의 장점을 살려 ID로 상품을 O(1)로 찾으세요.
     */
    public Product findProductById(String id) {
        // 여기에 로직 작성 (Hash 연습)
        return null;
    }
}
