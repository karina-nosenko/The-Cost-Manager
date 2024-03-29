package il.ac.shenkar.costmanager.server.models;

import il.ac.shenkar.costmanager.CostManagerException;

import java.util.List;

/**
 * Interface of the server models
 * @param <T> - one of the entities
 */
public interface IModel<T> {

    static String driver = "com.mysql.jdbc.Driver";
    static String connectionString = "jdbc:mysql://localhost:8889/costmanager";
    static final String db_user = "costroot";
    static final String db_password = "costtoor";

    public List<T> getAll() throws CostManagerException;
    public T getById(String id) throws CostManagerException;
    public void add(T obj) throws CostManagerException;
    void delete(String id) throws CostManagerException;
}
