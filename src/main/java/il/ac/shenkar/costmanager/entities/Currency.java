package il.ac.shenkar.costmanager.entities;

import static java.util.UUID.randomUUID;

/**
 * Class describes a currency
 */
public class Currency {

    private String currencyId;
    private String name;
    private double rate;

    /**
     * Default constructor
     */
    public Currency() {
        setCurrencyId("");
        setName("");
        setRate(0);
    }

    /**
     * Constructor
     * @param currencyId - if null - a random UUID will be set
     * @param name
     * @param rate
     */
    public Currency(String currencyId, String name, double rate) {

        // set random UUID if the currencyId is null
        if (currencyId == null || currencyId.isBlank()) {
            setCurrencyId(randomUUID().toString());
        } else {
            setCurrencyId(currencyId);
        }

        // set the rest of the parameters
        setName(name);
        setRate(rate);
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
     * @return rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * @param rate
     */
    public void setRate(double rate) {
        if (rate >= 0) {
            this.rate = rate;
        }
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

        Currency currency = (Currency)obj;
        return currency.getCurrencyId().equals(currencyId) &&
                currency.getName().equals(name) &&
                currency.getRate() == rate;
    }
}
