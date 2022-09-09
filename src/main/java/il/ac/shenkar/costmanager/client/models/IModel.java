package il.ac.shenkar.costmanager.client.models;

import il.ac.shenkar.costmanager.CostManagerException;

import java.util.List;

public interface IModel<T> {

    final static String api_url = "http://localhost:8080";

    public List<T> getAll() throws CostManagerException;
    public T getById(String id) throws CostManagerException;
    public void add(T obj) throws CostManagerException;
    void delete(String id) throws CostManagerException;
}
