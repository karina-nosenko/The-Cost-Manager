package il.ac.shenkar.costmanager.client.viewmodel;

import com.fasterxml.jackson.core.JsonProcessingException;
import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.client.models.IModel;
import il.ac.shenkar.costmanager.client.models.CategoryModel;
import il.ac.shenkar.costmanager.client.models.CostModel;
import il.ac.shenkar.costmanager.client.models.CurrencyModel;
import il.ac.shenkar.costmanager.client.models.UserModel;
import il.ac.shenkar.costmanager.client.view.IView;
import il.ac.shenkar.costmanager.entities.Category;
import il.ac.shenkar.costmanager.entities.Cost;
import il.ac.shenkar.costmanager.entities.Currency;

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
    public void getCosts() {
        service.submit(() -> {
            List<Cost> costs;
            try {
                costs = costModel.getByUserId("91966493-d06c-4593-bdb2-0fb1a084b6f8");
                for (var cost : costs) {
                    // replace categoryId by category name
                    Category category = categoryModel.getById(cost.getCategoryId());
                    cost.setCategoryId(category.getName());

                    // replace currencyId by currency name
                    Currency currency = currencyModel.getById(cost.getCurrencyId());
                    cost.setCurrencyId(currency.getName());
                }
            } catch (CostManagerException e) {
                throw new RuntimeException(e);
            }

            view.setCosts(costs);
        });
    }

    @Override
    public void addCategory(Category category) {
        service.submit(() -> {
            try {
                categoryModel.add(category);
            } catch (CostManagerException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            getCategories();
        });
    }

    @Override
    public void addCost(Cost cost) {
        service.submit(() -> {
            try {
                costModel.add(cost);
            } catch (CostManagerException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            getCosts();
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
