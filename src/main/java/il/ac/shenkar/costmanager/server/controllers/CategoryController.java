package il.ac.shenkar.costmanager.server.controllers;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Category;
import il.ac.shenkar.costmanager.entities.Currency;
import il.ac.shenkar.costmanager.server.models.CategoryModel;
import il.ac.shenkar.costmanager.server.models.CurrencyModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @GetMapping("/categories")
    public List<Category> getAll() throws ClassNotFoundException, CostManagerException {

        CategoryModel categoryModel = new CategoryModel();
        return categoryModel.getAll();
    }

    @GetMapping("/categories/{categoryId}")
    public Category getById(@PathVariable("categoryId") String categoryId) throws ClassNotFoundException, CostManagerException {

        CategoryModel categoryModel = new CategoryModel();
        return categoryModel.getById(categoryId);
    }

    @PostMapping("/categories")
    public void add(@RequestBody Category categoryObj) throws ClassNotFoundException, CostManagerException {

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.add(categoryObj);
    }

    @DeleteMapping("/categories/{categoryId}")
    public void delete(@PathVariable("categoryId") String categoryId) throws ClassNotFoundException, CostManagerException {

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.delete(categoryId);
    }
}
