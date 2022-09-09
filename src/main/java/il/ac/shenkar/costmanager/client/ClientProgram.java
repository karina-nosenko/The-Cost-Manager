package il.ac.shenkar.costmanager.client;

import il.ac.shenkar.costmanager.CategoryModel;
import il.ac.shenkar.costmanager.CostModel;
import il.ac.shenkar.costmanager.CurrencyModel;
import il.ac.shenkar.costmanager.UserModel;
import il.ac.shenkar.costmanager.client.view.IView;
import il.ac.shenkar.costmanager.client.view.View;
import il.ac.shenkar.costmanager.client.viewmodel.IViewModel;
import il.ac.shenkar.costmanager.client.viewmodel.ViewModel;

import javax.swing.*;

public class ClientProgram {
    public static void main(String[] args) {

        UserModel userModel = new UserModel();
        CurrencyModel currencyModel = new CurrencyModel();
        CostModel costModel = new CostModel();
        CategoryModel categoryModel = new CategoryModel();
        IViewModel vm = new ViewModel();

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
