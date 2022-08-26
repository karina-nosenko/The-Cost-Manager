package il.ac.shenkar.costmanager.entities;

import static java.util.UUID.randomUUID;

public class Category {

    private String categoryId;
    private String name;

    public Category() {
        setCategoryId("");
        setName("");
    }

    public Category(String categoryId, String name) {
        if (categoryId == null || categoryId.isBlank()) {
            setCategoryId(randomUUID().toString());
        } else {
            setCategoryId(categoryId);
        }

        setName(name);
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category {" +
                "categoryId=" + categoryId +
                ", name=" + name +
                '}';
    }
}
