package practice.order_system.model;

/**
 * 상품 엔티티
 */
public class Product {
    private String id;
    private String name;
    private String category;
    private int price;

    public Product(String id, String name, String category, int price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getId() { return id; }
    public String getCategory() { return category; }
    public int getPrice() { return price; }
    public String getName() { return name; }
}
