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
