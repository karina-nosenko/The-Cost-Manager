package il.ac.shenkar.costmanager.server.models;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Category;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CategoryModel implements IModel<Category> {

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

    public CategoryModel() throws CostManagerException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new CostManagerException(e.getMessage());
        }
    }

    public List<Category> getByUserId(String userId) throws CostManagerException {

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        List<Category> resultList = new LinkedList<>();

        try {
            connection = DriverManager.getConnection(connectionString, db_user, db_password);

            statement = connection.prepareStatement("SELECT categoryId, userId, name FROM categories WHERE userId = ?");

            statement.setString(1, userId);

            rs = statement.executeQuery();

            while(rs.next())
            {
                resultList.add(new Category(rs.getString("categoryId"), rs.getString("userId"), rs.getString("name")));
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
    public List<Category> getAll() throws CostManagerException {

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        List<Category> resultList = new LinkedList<>();

        try {
            connection = DriverManager.getConnection(connectionString, db_user, db_password);

            statement = connection.prepareStatement("SELECT categoryId, userId, name FROM categories");

            rs = statement.executeQuery();

            while(rs.next())
            {
                resultList.add(new Category(rs.getString("categoryId"), rs.getString("userId"), rs.getString("name")));
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
    public Category getById(String id) throws CostManagerException {
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;
        List<Category> resultList = new LinkedList<>();

        try {
            connection = DriverManager.getConnection(connectionString, db_user, db_password);

            statement = connection.prepareStatement("SELECT categoryId, userId, name FROM categories WHERE categoryId = ?");

            statement.setString(1, id);

            rs = statement.executeQuery();

            while(rs.next())
            {
                resultList.add(new Category(rs.getString("categoryId"), rs.getString("userId"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        finally {
            closeConnections(connection, rs, statement);
        }

        return resultList.size() > 0 ? resultList.get(0) : new Category();
    }

    @Override
    public void add(Category obj) throws CostManagerException {
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(connectionString, db_user, db_password);

            statement = connection.prepareStatement("INSERT INTO categories VALUES(?, ?, ?)");

            statement.setString(1, obj.getCategoryId());
            statement.setString(2, obj.getUserId());
            statement.setString(3, obj.getName());

            if (statement.executeUpdate() < 0) {
                throw new CostManagerException("Error while adding the category");
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

            statement = connection.prepareStatement("DELETE FROM categories WHERE categoryId = ? ");
            statement.setString(1, id);

            if (statement.executeUpdate() < 0) {
                throw new CostManagerException("Error while deleting the category");
            }
        } catch (SQLException e) {
            throw new CostManagerException(e.getMessage());
        }
        finally {
            closeConnections(connection, null, statement);
        }
    }
}
