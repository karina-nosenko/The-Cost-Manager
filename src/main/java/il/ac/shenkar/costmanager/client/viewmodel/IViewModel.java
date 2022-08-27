package il.ac.shenkar.costmanager.client.viewmodel;

import il.ac.shenkar.costmanager.client.model.CategoryModel;
import il.ac.shenkar.costmanager.client.model.CostModel;
import il.ac.shenkar.costmanager.client.model.CurrencyModel;
import il.ac.shenkar.costmanager.client.model.UserModel;
import il.ac.shenkar.costmanager.client.view.IView;
import il.ac.shenkar.costmanager.entities.Category;
import il.ac.shenkar.costmanager.entities.Cost;

public interface IViewModel {

    public void getCurrencies();
    public void getCategories();
    public void getCosts();
    public void addCategory(Category category);
    public void addCost(Cost cost);
    public void setUserModel(UserModel model);
    public void setCurrencyModel(CurrencyModel model);
    public void setCostModel(CostModel model);
    public void setCategoryModel(CategoryModel model);
    public void setView(IView view);
}
