package il.ac.shenkar.costmanager.server.models;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Cost;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that enables CRUD actions with the cost entity.
 * Communicates with the MySql database
 */
public class CostModel implements IModel<Cost> {

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
    public CostModel() throws CostManagerException {

        // initialize driver class
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new CostManagerException(e.getMessage());
        }
    }

    /**
     * Get all the costs of the given user
     * @param userId
     * @return costs list with the given userId
     * @throws CostManagerException
     */
    public List<Cost> getByUserId(String userId) throws CostManagerException {

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        List<Cost> resultList = new LinkedList<>();

        try {
            // get the costs list
            connection = DriverManager.getConnection(connectionString, db_user, db_password);
            statement = connection.prepareStatement("SELECT costId, userId, categoryId, sum, currencyId, description, creationDate FROM costs WHERE userId = ? ORDER BY creationDate");
            statement.setString(1, userId);
            rs = statement.executeQuery();

            // handle the response
            while(rs.next())
            {
                resultList.add(new Cost(
                        rs.getString("costId"),
                        rs.getString("userId"),
                        rs.getString("categoryId"),
                        rs.getDouble("sum"),
                        rs.getString("currencyId"),
                        rs.getString("description"),
                        rs.getString("creationDate")));
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
     * Get all costs
     * @return costs list
     * @throws CostManagerException
     */
    @Override
    public List<Cost> getAll() throws CostManagerException {

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        List<Cost> resultList = new LinkedList<>();

        try {
            // get the costs list
            connection = DriverManager.getConnection(connectionString, db_user, db_password);
            statement = connection.prepareStatement("SELECT costId, userId, categoryId, sum, currencyId, description, creationDate FROM costs");
            rs = statement.executeQuery();

            // handle the response
            while(rs.next())
            {
                resultList.add(new Cost(
                        rs.getString("costId"),
                        rs.getString("userId"),
                        rs.getString("categoryId"),
                        rs.getDouble("sum"),
                        rs.getString("currencyId"),
                        rs.getString("description"),
                        rs.getString("creationDate")));
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
     * Get cost by costId
     * @param id
     * @return cost with the given costId
     * @throws CostManagerException
     */
    @Override
    public Cost getById(String id) throws CostManagerException {
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        List<Cost> resultList = new LinkedList<>();

        try {
            // get the cost
            connection = DriverManager.getConnection(connectionString, db_user, db_password);
            statement = connection.prepareStatement("SELECT costId, userId, categoryId, sum, currencyId, description, creationDate FROM costs WHERE costId = ? ORDER BY creationDate");
            statement.setString(1, id);
            rs = statement.executeQuery();

            // handle response
            while(rs.next())
            {
                resultList.add(new Cost(
                        rs.getString("costId"),
                        rs.getString("userId"),
                        rs.getString("categoryId"),
                        rs.getDouble("sum"),
                        rs.getString("currencyId"),
                        rs.getString("description"),
                        rs.getString("creationDate")));
            }
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        finally {
            closeConnections(connection, rs, statement);
        }

        return resultList.size() > 0 ? resultList.get(0) : new Cost();
    }

    /**
     * Add new cost
     * @param obj - cost object
     * @throws CostManagerException
     */
    @Override
    public void add(Cost obj) throws CostManagerException {
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;

        try {
            // add the cost
            connection = DriverManager.getConnection(connectionString, db_user, db_password);
            statement = connection.prepareStatement("INSERT INTO costs VALUES(?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, obj.getCostId());
            statement.setString(2, obj.getUserId());
            statement.setString(3, obj.getCategoryId());
            statement.setDouble(4, obj.getSum());
            statement.setString(5, obj.getCurrencyId());
            statement.setString(6, obj.getDescription());
            statement.setString(7, obj.getCreationDate());

            if (statement.executeUpdate() < 0) {
                throw new CostManagerException("Error while adding the cost");
            }
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        finally {
            closeConnections(connection, rs, statement);
        }
    }

    /**
     * Delete a cost with the given costId
     * @param id
     * @throws CostManagerException
     */
    @Override
    public void delete(String id) throws CostManagerException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // delete the cost
            connection = DriverManager.getConnection(connectionString, db_user, db_password);
            statement = connection.prepareStatement("DELETE FROM costs WHERE costId = ? ");
            statement.setString(1, id);

            if (statement.executeUpdate() < 0) {
                throw new CostManagerException("Error while deleting the cost");
            }
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        finally {
            closeConnections(connection, null, statement);
        }
    }
}
