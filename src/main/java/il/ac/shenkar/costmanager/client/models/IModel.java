package il.ac.shenkar.costmanager.client.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Currency;

import java.util.List;

public interface IModel<T> {

    final static String api_url = "http://localhost:8080";

    public List<T> getAll() throws CostManagerException;
    public T getById(String id) throws CostManagerException;
    public void add(T obj) throws CostManagerException, JsonProcessingException;
}
