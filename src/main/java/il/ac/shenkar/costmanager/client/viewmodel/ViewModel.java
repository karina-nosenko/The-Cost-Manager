package il.ac.shenkar.costmanager.client.viewmodel;

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

import javax.swing.*;
import java.time.LocalDate;
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
            try {
                List<Currency> currencies = currencyModel.getAll();

                List<Currency> viewCurrencies = currencies;
                SwingUtilities.invokeLater(() -> {
                    view.setCurrencies(viewCurrencies);
                });
            } catch (CostManagerException e) {
                // TODO: VIEW MSG
            }
        });
    }

    @Override
    public void getCategories() {
        service.submit(() -> {
            try {
                List<Category> categories = categoryModel.getByUserId("91966493-d06c-4593-bdb2-0fb1a084b6f8");

                List<Category> viewCategories = categories;
                SwingUtilities.invokeLater(() -> {
                    view.setCategories(viewCategories);
                });
            } catch (CostManagerException e) {
                // TODO: VIEW MSG
            }
        });

        SwingUtilities.invokeLater(() -> {});
    }

    @Override
    public void getCosts(LocalDate from, LocalDate to) {
        service.submit(() -> {
            List<Cost> filteredCosts = new LinkedList<>();
            try {
                List<Cost> costs = costModel.getByUserId("91966493-d06c-4593-bdb2-0fb1a084b6f8");
                for (var cost : costs) {
                    String creationDateString = cost.getCreationDate();
                    LocalDate creationDate = LocalDate.parse(creationDateString);

                    if (from == null || to == null || creationDate.equals(from) || creationDate.equals(to) || (creationDate.isAfter(from) && creationDate.isBefore(to))) {
                        // replace categoryId by category name
                        Category category = categoryModel.getById(cost.getCategoryId());
                        cost.setCategoryId(category.getName());

                        // replace currencyId by currency name
                        Currency currency = currencyModel.getById(cost.getCurrencyId());
                        cost.setCurrencyId(currency.getName());

                        filteredCosts.add(cost);
                    }
                }

                SwingUtilities.invokeLater(() -> {
                    view.setCostsSize(costs.size(), filteredCosts.size());
                    view.setCosts(filteredCosts);
                });
            } catch (CostManagerException e) {
                // TODO: VIEW MSG
            }
        });

        SwingUtilities.invokeLater(() -> {});
    }

    @Override
    public void addCategory(Category category) {
        service.submit(() -> {
            try {
                categoryModel.add(category);
            } catch (CostManagerException e) {
                // TODO: VIEW MSG
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
                // TODO: VIEW MSG
            }

            getCosts(null, null);
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
