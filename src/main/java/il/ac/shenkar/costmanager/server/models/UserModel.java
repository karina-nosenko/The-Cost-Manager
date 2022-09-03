package il.ac.shenkar.costmanager.server.models;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserModel implements IModel<User> {

    private static void closeConnections(Connection connection, ResultSet rs, PreparedStatement statement) throws CostManagerException {
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CostManagerException(e.getMessage());
            }
        }
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new CostManagerException(e.getMessage());
            }
        }
        if(statement!=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new CostManagerException(e.getMessage());
            }
        }
    }

    public UserModel() throws CostManagerException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new CostManagerException(e.getMessage());
        }
    }

    public User login(String email, String password) throws CostManagerException {

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        List<User> resultList = new LinkedList<>();

        try {
            connection = DriverManager.getConnection(connectionString, db_user, db_password);

            statement = connection.prepareStatement("SELECT userId, username, email, password FROM users WHERE email = ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, password);

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

        return resultList.size() > 0 ? resultList.get(0) : null;
    }

    public void logup(User user) throws CostManagerException {

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        List<User> resultList = new LinkedList<>();

        try {
            connection = DriverManager.getConnection(connectionString, db_user, db_password);

            // check whether user with the given email already exists
            statement = connection.prepareStatement("SELECT userId, username, email, password FROM users WHERE email = ?");
            statement.setString(1, user.getEmail());

            rs = statement.executeQuery();

            while(rs.next())
            {
                resultList.add(new User(rs.getString("userId"),rs.getString("username"),rs.getString("email"),rs.getString("password")));
            }

            if (resultList.size() > 0) {
                throw new CostManagerException("User with this email already exists");
            }

            // add the new user
            add(user);
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        finally {
            closeConnections(connection, rs, statement);
        }
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

            if (statement.executeUpdate() < 0) {
                throw new CostManagerException("Error while adding the user");
            }
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        finally {
            closeConnections(connection, rs, statement);
        }
    }

    @Override
    public void delete(String id) throws CostManagerException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(connectionString, db_user, db_password);

            statement = connection.prepareStatement("DELETE FROM users WHERE userId = ? ");
            statement.setString(1, id);

            if (statement.executeUpdate() < 0) {
                throw new CostManagerException("Error while deleting the user");
            }
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        finally {
            closeConnections(connection, null, statement);
        }
    }
}
