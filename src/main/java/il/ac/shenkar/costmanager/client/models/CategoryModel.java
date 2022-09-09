package il.ac.shenkar.costmanager.client.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Category;
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
 * Class that enables CRUD actions with the category entity.
 * Communicates with the server category model.
 */
public class CategoryModel implements IModel<Category> {

    /**
     * Get all the categories of the given user
     * @param userId
     * @return categories list with the given userId
     * @throws CostManagerException
     */
    public List<Category> getByUserId(String userId) throws CostManagerException {

        // build the request
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/categories/users/" + userId))
                .GET()
                .build();

        // handle response
        List<Category> result = new LinkedList<>();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String stringResponse = response.body();
            JSONArray jsonArray = new JSONArray(stringResponse);

            for (int i=0; i< jsonArray.length(); i++) {
                JSONObject categoryObj = jsonArray.getJSONObject(i);
                Category category = new Category(
                        categoryObj.getString("categoryId"),
                        categoryObj.getString("userId"),
                        categoryObj.getString("name")
                );
                result.add(category);
            }
        } catch (IOException e) {
            throw new CostManagerException(e.getMessage());
        } catch (InterruptedException e) {
            throw new CostManagerException(e.getMessage());
        }

        return result;
    }

    /**
     * Get all categories
     * @return categories list
     * @throws CostManagerException
     */
    @Override
    public List<Category> getAll() throws CostManagerException {

        // build the request
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/categories"))
                .GET()
                .build();

        // handle response
        List<Category> result = new LinkedList<>();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String stringResponse = response.body();
            JSONArray jsonArray = new JSONArray(stringResponse);

            for (int i=0; i< jsonArray.length(); i++) {
                JSONObject categoryObj = jsonArray.getJSONObject(i);
                Category category = new Category(
                        categoryObj.getString("categoryId"),
                        categoryObj.getString("userId"),
                        categoryObj.getString("name")
                );
                result.add(category);
            }
        } catch (IOException e) {
            throw new CostManagerException(e.getMessage());
        } catch (InterruptedException e) {
            throw new CostManagerException(e.getMessage());
        }

        return result;
    }

    /**
     * Get category by categoryId
     * @param categoryId
     * @return category with the given categoryId
     * @throws CostManagerException
     */
    @Override
    public Category getById(String categoryId) throws CostManagerException {

        // build the request
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/categories/" + categoryId))
                .GET()
                .build();

        // handle response
        Category result = new Category();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String stringResponse = response.body();
            JSONObject categoryObj = new JSONObject(stringResponse);
            result = new Category(
                    categoryObj.getString("categoryId"),
                    categoryObj.getString("userId"),
                    categoryObj.getString("name")
            );
        } catch (IOException e) {
            throw new CostManagerException(e.getMessage());
        } catch (InterruptedException e) {
            throw new CostManagerException(e.getMessage());
        }

        return result;
    }

    /**
     * Add new category
     * @param obj - category object
     * @throws CostManagerException
     */
    @Override
    public void add(Category obj) throws CostManagerException {

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
                .uri(URI.create(api_url + "/categories"))
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
     * Delete a category with the given categoryId
     * @param categoryId
     * @throws CostManagerException
     */
    public void delete(String categoryId) throws CostManagerException {

        // build the request
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/categories/" + categoryId))
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
