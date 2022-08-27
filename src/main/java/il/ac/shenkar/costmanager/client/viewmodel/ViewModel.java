package il.ac.shenkar.costmanager.client.viewmodel;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.client.model.IModel;
import il.ac.shenkar.costmanager.client.model.CategoryModel;
import il.ac.shenkar.costmanager.client.model.CostModel;
import il.ac.shenkar.costmanager.client.model.CurrencyModel;
import il.ac.shenkar.costmanager.client.model.UserModel;
import il.ac.shenkar.costmanager.client.view.IView;
import il.ac.shenkar.costmanager.entities.Category;
import il.ac.shenkar.costmanager.entities.Currency;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModel implements IViewModel {

    private ExecutorService service;
    private IView view;
    private UserModel userModel;
    private CurrencyModel currencyModel;
    private CostModel costModel;
    private CategoryModel categoryModel;

    public ViewModel() {
        service = Executors.newFixedThreadPool(8);
    }

    public IView getView() {
        return view;
    }

    public IModel getUserModel() {
        return userModel;
    }

    public IModel getCurrencyModel() {
        return currencyModel;
    }

    public IModel getCostModel() {
        return costModel;
    }

    public IModel getCategoryModel() {
        return categoryModel;
    }

    @Override
    public void getCurrencies() {
        service.submit(() -> {
            List<Currency> currencies;
            try {
                currencies = currencyModel.getAll();
            } catch (CostManagerException e) {
                throw new RuntimeException(e);
            }

            view.setCurrencies(currencies);
        });
    }

    @Override
    public void getCategories() {
        service.submit(() -> {
            List<Category> categories;
            try {
                categories = categoryModel.getByUserId("91966493-d06c-4593-bdb2-0fb1a084b6f8");
            } catch (CostManagerException e) {
                throw new RuntimeException(e);
            }

            view.setCategories(categories);
        });
    }

    @Override
    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void setUserModel(UserModel model) {
        userModel = model;
    }

    @Override
    public void setCurrencyModel(CurrencyModel model) {
        currencyModel = model;
    }

    @Override
    public void setCostModel(CostModel model) {
        costModel = model;
    }

    @Override
    public void setCategoryModel(CategoryModel model) {
        categoryModel = model;
    }
}
