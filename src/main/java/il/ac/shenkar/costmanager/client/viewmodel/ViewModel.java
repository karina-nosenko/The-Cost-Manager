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
import il.ac.shenkar.costmanager.entities.User;

import javax.swing.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class that connects between client models and the view
 */
public class ViewModel implements IViewModel {

    private ExecutorService service;
    private IView view;
    private UserModel userModel;
    private CurrencyModel currencyModel;
    private CostModel costModel;
    private CategoryModel categoryModel;
    private String authorizedUserId = null;

    /**
     * Constructor.
     * Creates thread pool.
     */
    public ViewModel() {
        service = Executors.newFixedThreadPool(8);
    }

    /**
     * Login user
     * @param email
     * @param password
     */
    @Override
    public void loginUser(String email, String password) {
        final User[] user = new User[1];
        service.submit(() -> {
            try {
                // try to login the user
                user[0] = userModel.login(email, password);

                // the user with the given email or password was not found
                if (user[0] == null) {
                    SwingUtilities.invokeLater(() -> {
                        view.displayMessage("Invalid email or password", JOptionPane.ERROR_MESSAGE);
                    });
                } else {
                    // login the user
                    SwingUtilities.invokeLater(() -> {
                        authorizedUserId = user[0].getUserId();
                        view.loginUser();
                    });
                }
            } catch (CostManagerException e) {
                SwingUtilities.invokeLater(() -> {
                    view.displayMessage(e.getMessage(), JOptionPane.ERROR_MESSAGE);
                });
            }
        });
    }

    /**
     * Create new user
     * @param username
     * @param email
     * @param password
     */
    @Override
    public void logupUser(String username, String email, String password) {
        service.submit(() -> {
            try {
                // add new user and login
                userModel.logup(username, email, password);
                loginUser(email, password);
            } catch (CostManagerException e) {
                SwingUtilities.invokeLater(() -> {
                    view.displayMessage(e.getMessage(), JOptionPane.ERROR_MESSAGE);
                });
            }
        });
    }

    /**
     * Logout
     */
    @Override
    public void logout() {
        authorizedUserId = null;
    }

    /**
     * Get currencies and set in the view
     */
    @Override
    public void getCurrencies() {
        service.submit(() -> {
            try {
                // get currencies
                List<Currency> currencies = currencyModel.getAll();

                // set the currencies in the view
                List<Currency> viewCurrencies = currencies;
                SwingUtilities.invokeLater(() -> {
                    view.setCurrencies(viewCurrencies);
                });
            } catch (CostManagerException e) {
                SwingUtilities.invokeLater(() -> {
                    view.displayMessage(e.getMessage(), JOptionPane.ERROR_MESSAGE);
                });
            }
        });
    }

    /**
     * Get categories and set in the view
     */
    @Override
    public void getCategories() {
        service.submit(() -> {
            try {
                // get categories
                List<Category> categories = categoryModel.getByUserId(authorizedUserId);

                // set the categories in the view
                List<Category> viewCategories = categories;
                SwingUtilities.invokeLater(() -> {
                    view.setCategories(viewCategories);
                });
            } catch (CostManagerException e) {
                SwingUtilities.invokeLater(() -> {
                    view.displayMessage(e.getMessage(), JOptionPane.ERROR_MESSAGE);
                });
            }
        });

        SwingUtilities.invokeLater(() -> {});
    }

    /**
     * Get costs and set in the view
     */
    @Override
    public void getCosts(LocalDate from, LocalDate to) {
        service.submit(() -> {
            List<Cost> filteredCosts = new LinkedList<>();
            try {
                // get costs
                List<Cost> costs = costModel.getByUserId(authorizedUserId);
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

                // set the costs in the view
                SwingUtilities.invokeLater(() -> {
                    view.setCostsSize(costs.size(), filteredCosts.size());
                    view.setCosts(filteredCosts);
                });
            } catch (CostManagerException e) {
                SwingUtilities.invokeLater(() -> {
                    view.displayMessage(e.getMessage(), JOptionPane.ERROR_MESSAGE);
                });
            }
        });

        SwingUtilities.invokeLater(() -> {});
    }

    /**
     * Add new category to the db
     * @param category
     */
    @Override
    public void addCategory(Category category) {
        service.submit(() -> {
            try {
                categoryModel.add(category);
            } catch (CostManagerException e) {
                SwingUtilities.invokeLater(() -> {
                    view.displayMessage(e.getMessage(), JOptionPane.ERROR_MESSAGE);
                });
            }

            getCategories();
        });
    }

    /**
     * Add new cost to the db
     * @param cost
     */
    @Override
    public void addCost(Cost cost) {
        service.submit(() -> {
            try {
                costModel.add(cost);
            } catch (CostManagerException e) {
                SwingUtilities.invokeLater(() -> {
                    view.displayMessage(e.getMessage(), JOptionPane.ERROR_MESSAGE);
                });
            }

            getCosts(null, null);
        });
    }

    /**
     * @return view
     */
    public IView getView() {
        return view;
    }

    /**
     * @return userModel
     */
    public IModel getUserModel() {
        return userModel;
    }

    /**
     * @return currencyModel
     */
    public IModel getCurrencyModel() {
        return currencyModel;
    }

    /**
     * @return costModel
     */
    public IModel getCostModel() {
        return costModel;
    }

    /**
     * @return categoryModel
     */
    public IModel getCategoryModel() {
        return categoryModel;
    }

    /**
     * @return userId of the currently authorized user
     */
    @Override
    public String getAuthorizedUserId() { return authorizedUserId; }

    /**
     * @param view
     */
    @Override
    public void setView(IView view) {
        this.view = view;
    }

    /**
     * @param model
     */
    @Override
    public void setUserModel(UserModel model) {
        userModel = model;
    }

    /**
     * @param model
     */
    @Override
    public void setCurrencyModel(CurrencyModel model) {
        currencyModel = model;
    }

    /**
     * @param model
     */
    @Override
    public void setCostModel(CostModel model) {
        costModel = model;
    }

    /**
     * @param model
     */
    @Override
    public void setCategoryModel(CategoryModel model) {
        categoryModel = model;
    }
}
