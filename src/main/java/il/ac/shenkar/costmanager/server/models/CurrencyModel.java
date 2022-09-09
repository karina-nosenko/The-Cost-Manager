package il.ac.shenkar.costmanager.server.models;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Currency;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that enables CRUD actions with the currency entity.
 * Communicates with the MySql database
 */
public class CurrencyModel implements IModel<Currency> {

    private static void closeConnections(Connection connection, ResultSet rs, PreparedStatement statement) throws CostManagerException {

        // close connection
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CostManagerException(e.getMessage());
            }
        }

        // close result set
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new CostManagerException(e.getMessage());
            }
        }

        // close prepared statement
        if(statement!=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new CostManagerException(e.getMessage());
            }
        }
    }

    /**
     * Constructor
     * @throws CostManagerException
     */
    public CurrencyModel() throws CostManagerException {

        // initialize driver class
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new CostManagerException(e.getMessage());
        }
    }

    /**
     * Get all currencies
     * @return currencies list
     * @throws CostManagerException
     */
    @Override
    public List<Currency> getAll() throws CostManagerException {

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        List<Currency> resultList = new LinkedList<>();

        try {
            // get the currencies list
            connection = DriverManager.getConnection(connectionString, db_user, db_password);
            statement = connection.prepareStatement("SELECT currencyId, name, rate FROM currencies");
            rs = statement.executeQuery();

            // handle the response
            while(rs.next())
            {
                resultList.add(new Currency(rs.getString("currencyId"),rs.getString("name"),rs.getDouble("rate")));
            }
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        finally {
            closeConnections(connection, rs, statement);
        }

        return resultList;
    }

    /**
     * Get currency by currencyId
     * @param id
     * @return currency with the given currencyId
     * @throws CostManagerException
     */
    @Override
    public Currency getById(String id) throws CostManagerException {
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        List<Currency> resultList = new LinkedList<>();

        try {
            // get the currency
            connection = DriverManager.getConnection(connectionString, db_user, db_password);
            statement = connection.prepareStatement("SELECT currencyId, name, rate FROM currencies WHERE currencyId = ?");
            statement.setString(1, id);
            rs = statement.executeQuery();

            // handle response
            while(rs.next())
            {
                resultList.add(new Currency(rs.getString("currencyId"),rs.getString("name"),rs.getDouble("rate")));
            }
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        finally {
            closeConnections(connection, rs, statement);
        }

        return resultList.size() > 0 ? resultList.get(0) : new Currency();
    }

    /**
     * Add new currency
     * @param obj - currency object
     * @throws CostManagerException
     */
    @Override
    public void add(Currency obj) throws CostManagerException {
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;

        try {
            // add the currency
            connection = DriverManager.getConnection(connectionString, db_user, db_password);
            statement = connection.prepareStatement("INSERT INTO currencies VALUES(?, ?, ?)");
            statement.setString(1, obj.getCurrencyId());
            statement.setString(2, obj.getName());
            statement.setDouble(3, obj.getRate());

            if (statement.executeUpdate() < 0) {
                throw new CostManagerException("Error while adding the currency");
            }
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        finally {
            closeConnections(connection, rs, statement);
        }
    }

    /**
     * Delete a currency with the given currencyId
     * @param id
     * @throws CostManagerException
     */
    @Override
    public void delete(String id) throws CostManagerException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // delete the currency
            connection = DriverManager.getConnection(connectionString, db_user, db_password);
            statement = connection.prepareStatement("DELETE FROM currencies WHERE currencyId = ? ");
            statement.setString(1, id);

            if (statement.executeUpdate() < 0) {
                throw new CostManagerException("Error while deleting the currency");
            }
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        finally {
            closeConnections(connection, null, statement);
        }
    }
}
