package il.ac.shenkar.costmanager.entities;

import static java.util.UUID.randomUUID;

public class Currency {

    private String currencyId;
    private String name;
    private double rate;

    public Currency() {
        setCurrencyId("");
        setName("");
        setRate(0);
    }

    public Currency(String currencyId, String name, double rate) {
        if (currencyId == null || currencyId.isBlank()) {
            setCurrencyId(randomUUID().toString());
        } else {
            setCurrencyId(currencyId);
        }

        setName(name);
        setRate(rate);
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {

        Currency currency = (Currency)obj;
        return currency.getCurrencyId().equals(currencyId) &&
                currency.getName().equals(name) &&
                currency.getRate() == rate;
    }
}
