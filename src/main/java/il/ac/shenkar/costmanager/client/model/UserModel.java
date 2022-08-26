package il.ac.shenkar.costmanager.client.model;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserModel implements IModel<User> {

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

    public UserModel() throws ClassNotFoundException {
        Class.forName(driver);
    }

    @Override
    public List<User> getAll() throws CostManagerException {

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        List<User> resultList = new LinkedList<>();

        try {
            connection = DriverManager.getConnection(connectionString, db_user, db_password);

            statement = connection.prepareStatement("SELECT userId, username, email, password FROM users");

            rs = statement.executeQuery();

            while(rs.next())
            {
                resultList.add(new User(rs.getString("userId"),rs.getString("username"),rs.getString("email"),rs.getString("password")));
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
    public User getById(String id) throws CostManagerException {
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        List<User> resultList = new LinkedList<>();

        try {
            connection = DriverManager.getConnection(connectionString, db_user, db_password);

            statement = connection.prepareStatement("SELECT userId, username, email, password FROM users WHERE userId = ?");

            statement.setString(1, id);

            rs = statement.executeQuery();

            while(rs.next())
            {
                resultList.add(new User(rs.getString("userId"),rs.getString("username"),rs.getString("email"),rs.getString("password")));
            }
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        finally {
            closeConnections(connection, rs, statement);
        }

        return resultList.size() > 0 ? resultList.get(0) : new User();
    }

    @Override
    public void add(User obj) throws CostManagerException {
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(connectionString, db_user, db_password);

            statement = connection.prepareStatement("INSERT INTO users VALUES(?, ?, ?, ?)");

            statement.setString(1, obj.getUserId());
            statement.setString(2, obj.getUsername());
            statement.setString(3, obj.getEmail());
            statement.setString(4, obj.getPassword());

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
