package il.ac.shenkar.costmanager.client.view;

import il.ac.shenkar.costmanager.client.viewmodel.IViewModel;

public interface IView {

    public void setViewModel(IViewModel ob);
    public void start();
}
