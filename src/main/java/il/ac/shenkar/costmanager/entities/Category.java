package il.ac.shenkar.costmanager.entities;

import static java.util.UUID.randomUUID;

public class Category {

    private String categoryId;
    private String userId;
    private String name;

    public Category() {
        setCategoryId("");
        setUserId("");
        setName("");
    }

    public Category(String categoryId, String userId, String name) {
        if (categoryId == null || categoryId.isBlank()) {
            setCategoryId(randomUUID().toString());
        } else {
            setCategoryId(categoryId);
        }
        setUserId(userId);
        setName(name);
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {

        Category category = (Category)obj;
        return category.getUserId().equals(userId) &&
                category.getUserId().equals(userId) &&
                category.getName().equals(name);
    }
}
