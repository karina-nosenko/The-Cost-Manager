package il.ac.shenkar.costmanager.client.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import il.ac.shenkar.costmanager.CostManagerException;

import java.util.List;

public interface IModel<T> {

    static String driver = "com.mysql.jdbc.Driver";
    static String connectionString = "jdbc:mysql://localhost:8889/costmanager";
    static final String db_user = "costroot";
    static final String db_password = "costtoor";

    public List<T> getAll() throws CostManagerException;
    public T getById(String id) throws CostManagerException;
    public void add(T obj) throws CostManagerException, JsonProcessingException;
}
