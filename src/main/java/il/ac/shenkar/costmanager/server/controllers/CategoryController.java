package il.ac.shenkar.costmanager.server.controllers;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Category;
import il.ac.shenkar.costmanager.server.models.CategoryModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Categories REST API
 */
@RestController
public class CategoryController {

    /**
     * Get all categories
     * @return categories list
     * @throws CostManagerException
     */
    @GetMapping("/categories")
    public List<Category> getAll() throws CostManagerException {

        CategoryModel categoryModel = new CategoryModel();
        return categoryModel.getAll();
    }

    /**
     * Get all the categories of the given user
     * @param userId
     * @return categories list with the given userId
     * @throws CostManagerException
     */
    @GetMapping("/categories/users/{userId}")
    public List<Category> getByUserId(@PathVariable("userId") String userId) throws CostManagerException {

        CategoryModel categoryModel = new CategoryModel();
        return categoryModel.getByUserId(userId);
    }

    /**
     * Get category by categoryId
     * @param categoryId
     * @return category with the given categoryId
     * @throws CostManagerException
     */
    @GetMapping("/categories/{categoryId}")
    public Category getById(@PathVariable("categoryId") String categoryId) throws CostManagerException {

        CategoryModel categoryModel = new CategoryModel();
        return categoryModel.getById(categoryId);
    }

    /**
     * Add new category
     * @body categoryObj
     * @throws CostManagerException
     */
    @PostMapping("/categories")
    public void add(@RequestBody Category categoryObj) throws CostManagerException {

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.add(categoryObj);
    }

    /**
     * Delete a category with the given categoryId
     * @param categoryId
     * @throws CostManagerException
     */
    @DeleteMapping("/categories/{categoryId}")
    public void delete(@PathVariable("categoryId") String categoryId) throws CostManagerException {

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.delete(categoryId);
    }
}
