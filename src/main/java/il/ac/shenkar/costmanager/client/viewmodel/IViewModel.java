package il.ac.shenkar.costmanager.client.viewmodel;

import il.ac.shenkar.costmanager.client.models.CategoryModel;
import il.ac.shenkar.costmanager.client.models.CostModel;
import il.ac.shenkar.costmanager.client.models.CurrencyModel;
import il.ac.shenkar.costmanager.client.models.UserModel;
import il.ac.shenkar.costmanager.client.view.IView;
import il.ac.shenkar.costmanager.entities.Category;
import il.ac.shenkar.costmanager.entities.Cost;

import java.time.LocalDate;

public interface IViewModel {

    public String getAuthorizedUserId();
    public void loginUser(String email, String password);
    public void logupUser(String username, String email, String password);
    public void logout();
    public void getCurrencies();
    public void getCategories();
    public void getCosts(LocalDate from, LocalDate to);
    public void addCategory(Category category);
    public void addCost(Cost cost);
    public void setUserModel(UserModel model);
    public void setCurrencyModel(CurrencyModel model);
    public void setCostModel(CostModel model);
    public void setCategoryModel(CategoryModel model);
    public void setView(IView view);
}
