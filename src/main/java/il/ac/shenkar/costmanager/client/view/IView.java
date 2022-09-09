package il.ac.shenkar.costmanager.client.view;

import il.ac.shenkar.costmanager.client.viewmodel.IViewModel;
import il.ac.shenkar.costmanager.entities.Category;
import il.ac.shenkar.costmanager.entities.Cost;
import il.ac.shenkar.costmanager.entities.Currency;
import il.ac.shenkar.costmanager.entities.User;

import java.util.List;

/**
 * Interface of the view
 */
public interface IView {

    public void displayMessage(String message, int option);
    public void loginUser();
    public void setViewModel(IViewModel ob);
    public void setCategories(List<Category> categories);
    public void setCosts(List<Cost> costs);
    public void setCurrencies(List<Currency> currencies);
    void setCostsSize(int size, int filteredSize);
    public void start();
}
