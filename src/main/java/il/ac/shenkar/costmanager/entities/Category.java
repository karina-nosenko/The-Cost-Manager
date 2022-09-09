package il.ac.shenkar.costmanager.entities;

import static java.util.UUID.randomUUID;

/**
 * Class describes a category of a cost
 */
public class Category {

    private String categoryId;
    private String userId;
    private String name;

    /**
     * Default constructor
     */
    public Category() {
        setCategoryId("");
        setUserId("");
        setName("");
    }

    /**
     * Constructor
     * @param categoryId - if null - a random UUID will be set
     * @param userId
     * @param name
     */
    public Category(String categoryId, String userId, String name) {

        // set random UUID if the categoriId is null
        if (categoryId == null || categoryId.isBlank()) {
            setCategoryId(randomUUID().toString());
        } else {
            setCategoryId(categoryId);
        }

        // set the rest of the parameters
        setUserId(userId);
        setName(name);
    }

    /**
     * @return categoryId
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Overrides the toString() method
     * @return name
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Overrides equals() method
     * @param obj
     * @return boolean whether obj parameters are equal to the object's
     */
    @Override
    public boolean equals(Object obj) {

        Category category = (Category)obj;
        return category.getUserId().equals(userId) &&
                category.getUserId().equals(userId) &&
                category.getName().equals(name);
    }
}
