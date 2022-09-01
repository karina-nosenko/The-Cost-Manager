package il.ac.shenkar.costmanager.server.models;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Currency;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CurrencyModel implements IModel<Currency> {

    private static void closeConnections(Connection connection, ResultSet rs, PreparedStatement statement) {
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public CurrencyModel() throws ClassNotFoundException {
        Class.forName(driver);
    }

    public void delete(String id) throws CostManagerException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(connectionString, db_user, db_password);

            statement = connection.prepareStatement("DELETE FROM currencies WHERE currencyId = ? ");
            statement.setString(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        finally {
            closeConnections(connection, null, statement);
        }
    }

    @Override
    public List<Currency> getAll() throws CostManagerException {

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        List<Currency> resultList = new LinkedList<>();

        try {
            connection = DriverManager.getConnection(connectionString, db_user, db_password);

            statement = connection.prepareStatement("SELECT currencyId, name, rate FROM currencies");

            rs = statement.executeQuery();

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

    @Override
    public Currency getById(String id) throws CostManagerException {
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        List<Currency> resultList = new LinkedList<>();

        try {
            connection = DriverManager.getConnection(connectionString, db_user, db_password);

            statement = connection.prepareStatement("SELECT currencyId, name, rate FROM currencies WHERE currencyId = ?");
            statement.setString(1, id);

            rs = statement.executeQuery();

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

    @Override
    public void add(Currency obj) throws CostManagerException {
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(connectionString, db_user, db_password);

            statement = connection.prepareStatement("INSERT INTO currencies VALUES(?, ?, ?)");
            statement.setString(1, obj.getCurrencyId());
            statement.setString(2, obj.getName());
            statement.setDouble(3, obj.getRate());

            statement.addBatch();
            statement.executeBatch();
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        finally {
            closeConnections(connection, rs, statement);
        }
    }
}
