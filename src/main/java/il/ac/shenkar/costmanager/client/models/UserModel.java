package il.ac.shenkar.costmanager.client.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Category;
import il.ac.shenkar.costmanager.entities.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserModel implements IModel<User> {

    @Override
    public List<User> getAll() throws CostManagerException {

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/users"))
                .GET()
                .build();

        List<User> result = new LinkedList<>();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String stringResponse = response.body();
            JSONArray jsonArray = new JSONArray(stringResponse);

            for (int i=0; i< jsonArray.length(); i++) {
                JSONObject userObj = jsonArray.getJSONObject(i);
                User user = new User(
                        userObj.getString("userId"),
                        userObj.getString("username"),
                        userObj.getString("email"),
                        userObj.getString("password")
                );
                result.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public User getById(String categoryId) throws CostManagerException {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/users/" + categoryId))
                .GET()
                .build();

        User result = new User();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String stringResponse = response.body();
            JSONObject userObj = new JSONObject(stringResponse);
            result = new User(
                    userObj.getString("userId"),
                    userObj.getString("username"),
                    userObj.getString("email"),
                    userObj.getString("password")
            );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void add(User obj) throws CostManagerException, JsonProcessingException {

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(obj);

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/users"))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int status = response.statusCode();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
