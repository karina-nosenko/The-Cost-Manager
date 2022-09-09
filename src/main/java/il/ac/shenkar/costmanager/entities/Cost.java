package il.ac.shenkar.costmanager.entities;

import java.time.LocalDate;

import static java.util.UUID.randomUUID;

/**
 * Class describes a cost of a user
 */
public class Cost {

    private String costId;
    private String userId;
    private String categoryId;
    private double sum;
    private String currencyId;
    private String description;
    private String creationDate;

    /**
     * Default constructor
     */
    public Cost() {
        setCostId("");
        setUserId("");
        setCategoryId("");
        setSum(0);
        setCurrencyId("");
        setDescription("");
        setCreationDate("");
    }

    /**
     * Constructor
     * @param costId - if null - a random UUID will be set
     * @param userId
     * @param categoryId
     * @param sum
     * @param currencyId
     * @param description
     * @param creationDate - if null - the current local date will be set
     */
    public Cost(String costId, String userId, String categoryId, double sum, String currencyId, String description, String creationDate) {

        // set random UUID if the costId is null
        if (costId == null || costId.isBlank()) {
            setCostId(randomUUID().toString());
        } else {
            setCostId(costId);
        }

        // set current local date if the creationDate is null
        if (creationDate == null || creationDate.isBlank()) {
            creationDate = LocalDate.now().toString();
        }

        // set the rest of the parameters
        setCreationDate(creationDate);
        setCategoryId(categoryId);
        setUserId(userId);
        setSum(sum);
        setCurrencyId(currencyId);
        setDescription(description);
    }

    /**
     * @return costId
     */
    public String getCostId() {
        return costId;
    }

    /**
     * @param costId
     */
    public void setCostId(String costId) {
        this.costId = costId;
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
     * @return sum
     */
    public double getSum() {
        return sum;
    }

    /**
     * @param sum
     */
    public void setSum(double sum) {
        if (sum >= 0) {
            this.sum = sum;
        }
    }

    /**
     * @return currencyId
     */
    public String getCurrencyId() {
        return currencyId;
    }

    /**
     * @param currencyId
     */
    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return creationDate
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Overrides the toString() method
     * @return serialized params
     */
    @Override
    public String toString() {
        return "Cost {" +
                "costId=" + costId +
                "userId=" + userId +
                ", categoryId=" + categoryId +
                ", sum=" + sum +
                ", currencyId=" + currencyId +
                ", description=" + description +
                ", creationDate=" + creationDate +
                '}';
    }

    /**
     * Overrides equals() method
     * @param obj
     * @return boolean whether obj parameters are equal to the object's
     */
    @Override
    public boolean equals(Object obj) {

        Cost cost = (Cost)obj;
        return cost.getCostId().equals(costId) &&
                cost.getUserId().equals(userId) &&
                cost.getCategoryId().equals(categoryId) &&
                cost.getSum() == sum &&
                cost.getCurrencyId().equals(currencyId) &&
                cost.getDescription().equals(description) &&
                cost.getCreationDate().equals(creationDate);
    }
}
