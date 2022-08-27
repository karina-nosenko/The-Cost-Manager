package il.ac.shenkar.costmanager.entities;

import java.sql.Timestamp;

import static java.util.UUID.randomUUID;

public class Cost {

    private String costId;
    private String userId;
    private String categoryId;
    private double sum;
    private String currencyId;
    private String description;
    private String creationDate;

    public Cost() {
        setCostId("");
        setUserId("");
        setCategoryId("");
        setSum(0);
        setCurrencyId("");
        setDescription("");
        setCreationDate("");
    }

    public Cost(String costId, String userId, String categoryId, double sum, String currencyId, String description, String creationDate) {
        if (costId == null || costId.isBlank()) {
            setCostId(randomUUID().toString());
        } else {
            setCostId(costId);
        }

        if (creationDate == null || creationDate.isBlank()) {
            setCreationDate((new Timestamp(System.currentTimeMillis()).toString()));
        } else {
            setCreationDate(creationDate);
        }

        setCategoryId(categoryId);
        setUserId(userId);
        setSum(sum);
        setCurrencyId(currencyId);
        setDescription(description);
    }

    public String getCostId() {
        return costId;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Cost {" +
                "costId=" + costId +
                ", categoryId=" + categoryId +
                ", sum=" + sum +
                ", currencyId=" + currencyId +
                ", description=" + description +
                ", creationDate=" + creationDate +
                '}';
    }
}
