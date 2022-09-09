package il.ac.shenkar.costmanager.client.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that enables CRUD actions with the user entity.
 * Communicates with the server user model.
 */
public class UserModel implements IModel<User> {

    /**
     * Find the user with the given email and password.
     * @param email
     * @param password
     * @return logged in user or null if user not found
     * @throws CostManagerException
     */
    public User login(String email, String password) throws CostManagerException {

        // build the request
        var authObj = new User(null, null, email, password);
        var objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(authObj);
        } catch (JsonProcessingException e) {
            throw new CostManagerException(e.toString());
        }

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/auth/login"))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // handle response
        User result;
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String stringResponse = response.body();
            if (stringResponse.isEmpty()) {
                result = null;
            } else {
                JSONObject userObj = new JSONObject(stringResponse);
                result = new User(
                        userObj.getString("userId"),
                        userObj.getString("username"),
                        userObj.getString("email"),
                        userObj.getString("password")
                );
            }
        } catch (IOException e) {
            throw new CostManagerException(e.getMessage());
        } catch (InterruptedException e) {
            throw new CostManagerException(e.getMessage());
        }

        return result;
    }

    /**
     * Create new user.
     * If a user with the given email already exists - exception will be thrown.
     * @param username
     * @param email
     * @param password
     * @throws CostManagerException
     */
    public void logup(String username, String email, String password) throws CostManagerException {

        // build the request
        var obj = new User(null, username, email, password);
        var objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new CostManagerException(e.toString());
        }

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/auth/logup"))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // handle response
        try {
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() != 200) {
                throw new CostManagerException("User with this email already exists");
            }
        } catch (IOException e) {
            throw new CostManagerException(e.getMessage());
        } catch (InterruptedException e) {
            throw new CostManagerException(e.getMessage());
        }
    }

    /**
     * Get all users
     * @return users list
     * @throws CostManagerException
     */
    @Override
    public List<User> getAll() throws CostManagerException {

        // build the request
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/users"))
                .GET()
                .build();

        // handle response
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
            throw new CostManagerException(e.getMessage());
        } catch (InterruptedException e) {
            throw new CostManagerException(e.getMessage());
        }

        return result;
    }

    /**
     * Get user by userId
     * @param userId
     * @return user with the given userId
     * @throws CostManagerException
     */
    @Override
    public User getById(String userId) throws CostManagerException {

        // build the request
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/users/" + userId))
                .GET()
                .build();

        // handle response
        User result;
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
            throw new CostManagerException(e.getMessage());
        } catch (InterruptedException e) {
            throw new CostManagerException(e.getMessage());
        }

        return result;
    }

    /**
     * Add new user
     * @param obj - user object
     * @throws CostManagerException
     */
    @Override
    public void add(User obj) throws CostManagerException {

        // build the request
        var objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new CostManagerException(e.toString());
        }

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/users"))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // handle response
        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new CostManagerException(e.getMessage());
        } catch (InterruptedException e) {
            throw new CostManagerException(e.getMessage());
        }
    }

    /**
     * Delete a user with the given userId
     * @param userId
     * @throws CostManagerException
     */
    public void delete(String userId) throws CostManagerException {

        // build the request
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/users/" + userId))
                .DELETE()
                .build();

        // handle response
        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new CostManagerException(e.getMessage());
        } catch (InterruptedException e) {
            throw new CostManagerException(e.getMessage());
        }
    }
}
