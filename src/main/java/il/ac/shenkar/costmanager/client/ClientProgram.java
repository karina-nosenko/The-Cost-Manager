package il.ac.shenkar.costmanager.client;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.client.model.CategoryModel;
import il.ac.shenkar.costmanager.client.model.CostModel;
import il.ac.shenkar.costmanager.client.model.CurrencyModel;
import il.ac.shenkar.costmanager.client.model.UserModel;
import il.ac.shenkar.costmanager.client.view.IView;
import il.ac.shenkar.costmanager.client.view.View;
import il.ac.shenkar.costmanager.client.viewmodel.IViewModel;
import il.ac.shenkar.costmanager.client.viewmodel.ViewModel;
import il.ac.shenkar.costmanager.entities.Cost;

import javax.swing.*;

public class ClientProgram {
    public static void main(String[] args) throws ClassNotFoundException {

        UserModel userModel = new UserModel();
        CurrencyModel currencyModel = new CurrencyModel();
        CostModel costModel = new CostModel();
        CategoryModel categoryModel = new CategoryModel();
        IViewModel vm = new ViewModel();

//        categoryModel.add(new Category(null, "91966493-d06c-4593-bdb2-0fb1a084b6f8", "Groceries"));
//        categoryModel.add(new Category(null, "91966493-d06c-4593-bdb2-0fb1a084b6f8", "Transport"));
//        categoryModel.add(new Category(null, "91966493-d06c-4593-bdb2-0fb1a084b6f8", "Leisure"));
//        categoryModel.add(new Category(null, "91966493-d06c-4593-bdb2-0fb1a084b6f8", "Purchases"));
//        categoryModel.add(new Category(null, "91966493-d06c-4593-bdb2-0fb1a084b6f8", "Health"));
//        categoryModel.add(new Category(null, "91966493-d06c-4593-bdb2-0fb1a084b6f8", "Gifts"));

//        try {
//            costModel.add(new Cost(null, "91966493-d06c-4593-bdb2-0fb1a084b6f8", "c63f7abb-17e2-4c5d-9bdc-8939c83c0144", 500, "c554b888-63ab-4ea6-b648-07d1a61d3039", "Student transport pack for semester", null));
//        } catch (CostManagerException e) {
//            throw new RuntimeException(e);
//        }

        SwingUtilities.invokeLater(() -> {
            IView view = new View();
            view.start();
            view.setViewModel(vm);
            vm.setView(view);
            vm.setUserModel(userModel);
            vm.setCurrencyModel(currencyModel);
            vm.setCostModel(costModel);
            vm.setCategoryModel(categoryModel);
        });
    }
}
