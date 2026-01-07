package practice.order_system.model;

/**
 * 사용자 엔티티
 */
public class User {
    private String id;
    private String name;
    private String grade; // BRONZE, SILVER, GOLD
    private boolean isActive;

    public User(String id, String name, String grade, boolean isActive) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.isActive = isActive;
    }

    public String getId() { return id; }
    public String getGrade() { return grade; }
    public boolean isActive() { return isActive; }
    public String getName() { return name; }
}
