package il.ac.shenkar.costmanager;

import il.ac.shenkar.costmanager.entities.Category;
import il.ac.shenkar.costmanager.entities.Cost;
import il.ac.shenkar.costmanager.entities.Currency;
import il.ac.shenkar.costmanager.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class costManagerTest {

    UserModel userModel;
    CategoryModel categoryModel;
    CostModel costModel;
    CurrencyModel currencyModel;

    @BeforeEach
    void setUp() {

        // initialize models
        userModel = new UserModel();
        categoryModel = new CategoryModel();
        costModel = new CostModel();
        currencyModel = new CurrencyModel();
    }

    @Test
    public void testModels() throws CostManagerException {

        testUserModel();
        testCategoryModel();
        testCostModel();
        testCurrencyModel();
    }

    public void testUserModel() throws CostManagerException {

        String testUserId = "123";
        User testUser = new User(testUserId, "testUser", "testUser@gmail.com", "testUser123");

        // test adding a user
        userModel.add(testUser);

        // test getting a user by id
        User user = userModel.getById(testUserId);
        assertEquals(testUserId, user.getUserId());
        assertEquals("testUser", user.getUsername());
        assertEquals("testUser@gmail.com", user.getEmail());
        assertEquals("testUser123", user.getPassword());

        // test getting all users
        List<User> users = userModel.getAll();
        assertTrue(users.contains(user));

        // test deleting a user
        userModel.delete(testUserId);
        users = userModel.getAll();
        assertFalse(users.contains(user));
    }

    public void testCategoryModel() throws CostManagerException {

        String testCategoryId = "123";
        String testUserId = "456";
        Category testCategory = new Category(testCategoryId, testUserId, "testCategory");

        // test adding a category
        categoryModel.add(testCategory);

        // test getting a category by id
        Category category = categoryModel.getById(testCategoryId);
        assertEquals(testCategoryId, category.getCategoryId());
        assertEquals(testUserId, category.getUserId());
        assertEquals("testCategory", category.getName());

        // test getting a category by user id
        List<Category> categoriesByUserId = categoryModel.getByUserId(testUserId);
        assertTrue(categoriesByUserId.contains(category));

        // test getting all categories
        List<Category> categories = categoryModel.getAll();
        assertTrue(categories.contains(category));

        // test deleting a category
        categoryModel.delete(testCategoryId);
        categories = categoryModel.getAll();
        assertFalse(categories.contains(category));
    }

    public void testCostModel() throws CostManagerException {

        String testCostId = "123";
        String testUserId = "456";
        Cost testCost = new Cost(testCostId, testUserId, "categoryId", 100.5, "currencyId", "description", "2022-09-09");

        // test adding a cost
        costModel.add(testCost);

        // test getting a cost by id
        Cost cost = costModel.getById(testCostId);
        assertEquals(testCostId, cost.getCostId());
        assertEquals(testUserId, cost.getUserId());
        assertEquals("categoryId", cost.getCategoryId());
        assertEquals(100.5, cost.getSum());
        assertEquals("currencyId", cost.getCurrencyId());
        assertEquals("description", cost.getDescription());
        assertEquals("2022-09-09", cost.getCreationDate());

        // test getting a cost by user id
        List<Cost> categoriesByUserId = costModel.getByUserId(testUserId);
        assertTrue(categoriesByUserId.contains(cost));

        // test getting all costs
        List<Cost> costs = costModel.getAll();
        assertTrue(costs.contains(cost));

        // test deleting a cost
        costModel.delete(testCostId);
        costs = costModel.getAll();
        assertFalse(costs.contains(cost));
    }

    public void testCurrencyModel() throws CostManagerException {

        String testCurrencyId = "123";
        Currency testCurrency = new Currency(testCurrencyId, "currencyName", 10.5);

        // test adding a user
        currencyModel.add(testCurrency);

        // test getting a user by id
        Currency currency = currencyModel.getById(testCurrencyId);
        assertEquals(testCurrencyId, currency.getCurrencyId());
        assertEquals("currencyName", currency.getName());
        assertEquals(10.5, currency.getRate());

        // test getting all users
        List<Currency> currencies = currencyModel.getAll();
        assertTrue(currencies.contains(currency));

        // test deleting a user
        currencyModel.delete(testCurrencyId);
        currencies = currencyModel.getAll();
        assertFalse(currencies.contains(currency));
    }
}
