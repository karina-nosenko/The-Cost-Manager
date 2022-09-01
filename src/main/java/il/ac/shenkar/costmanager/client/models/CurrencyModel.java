package il.ac.shenkar.costmanager.client.models;

import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Currency;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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

    @Override
    public List<Currency> getAll() throws CostManagerException {

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/currencies"))
                .GET()
                .build();

        List<Currency> result = new LinkedList<>();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String stringResponse = response.body();
            JSONArray jsonArray = new JSONArray(stringResponse);

            for (int i=0; i< jsonArray.length(); i++) {
                JSONObject currencyObj = jsonArray.getJSONObject(i);
                Currency currency = new Currency(
                        currencyObj.getString("currencyId"),
                        currencyObj.getString("name"),
                        currencyObj.getDouble("rate")
                );
                result.add(currency);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
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
