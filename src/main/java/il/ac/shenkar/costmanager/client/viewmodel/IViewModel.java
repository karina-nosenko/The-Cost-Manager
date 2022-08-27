package il.ac.shenkar.costmanager.client.viewmodel;

import il.ac.shenkar.costmanager.client.model.CategoryModel;
import il.ac.shenkar.costmanager.client.model.CostModel;
import il.ac.shenkar.costmanager.client.model.CurrencyModel;
import il.ac.shenkar.costmanager.client.model.UserModel;
import il.ac.shenkar.costmanager.client.view.IView;
import il.ac.shenkar.costmanager.entities.Category;

public interface IViewModel {

    public void getCurrencies();
    public void getCategories();
    public void addCategory(Category category);
    public void setUserModel(UserModel model);
    public void setCurrencyModel(CurrencyModel model);
    public void setCostModel(CostModel model);
    public void setCategoryModel(CategoryModel model);
    public void setView(IView view);
}
