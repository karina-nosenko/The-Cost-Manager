package il.ac.shenkar.costmanager.client.view;

import il.ac.shenkar.costmanager.client.viewmodel.IViewModel;
import il.ac.shenkar.costmanager.entities.Category;
import il.ac.shenkar.costmanager.entities.Cost;
import il.ac.shenkar.costmanager.entities.Currency;
import il.ac.shenkar.costmanager.entities.User;

import java.util.List;

public interface IView {

    public void displayMessage(String message, int option);
    public void setViewModel(IViewModel ob);
    public void setCategories(List<Category> categories);
    public void setCosts(List<Cost> costs);
    public void setCurrencies(List<Currency> currencies);
    public void setUsers(List<User> users);
    void setCostsSize(int size, int filteredSize);
    public void start();
}
