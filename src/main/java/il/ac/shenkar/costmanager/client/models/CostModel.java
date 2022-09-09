package il.ac.shenkar.costmanager.client.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Cost;
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
 * Class that enables CRUD actions with the cost entity.
 * Communicates with the server cost model.
 */
public class CostModel implements IModel<Cost> {

    /**
     * Get all the costs of the given user
     * @param userId
     * @return costs list with the given userId
     * @throws CostManagerException
     */
    public List<Cost> getByUserId(String userId) throws CostManagerException {

        // build the request
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/costs/users/" + userId))
                .GET()
                .build();

        // handle response
        List<Cost> result = new LinkedList<>();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String stringResponse = response.body();
            JSONArray jsonArray = new JSONArray(stringResponse);

            for (int i=0; i< jsonArray.length(); i++) {
                JSONObject costObj = jsonArray.getJSONObject(i);
                Cost cost = new Cost(
                        costObj.getString("costId"),
                        costObj.getString("userId"),
                        costObj.getString("categoryId"),
                        costObj.getDouble("sum"),
                        costObj.getString("currencyId"),
                        costObj.getString("description"),
                        costObj.getString("creationDate")
                );
                result.add(cost);
            }
        } catch (IOException e) {
            throw new CostManagerException(e.getMessage());
        } catch (InterruptedException e) {
            throw new CostManagerException(e.getMessage());
        }

        return result;
    }

    /**
     * Get all costs
     * @return costs list
     * @throws CostManagerException
     */
    @Override
    public List<Cost> getAll() throws CostManagerException {

        // build the request
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/costs"))
                .GET()
                .build();

        // handle response
        List<Cost> result = new LinkedList<>();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String stringResponse = response.body();
            JSONArray jsonArray = new JSONArray(stringResponse);

            for (int i=0; i< jsonArray.length(); i++) {
                JSONObject costObj = jsonArray.getJSONObject(i);
                Cost cost = new Cost(
                        costObj.getString("costId"),
                        costObj.getString("userId"),
                        costObj.getString("categoryId"),
                        costObj.getDouble("sum"),
                        costObj.getString("currencyId"),
                        costObj.getString("description"),
                        costObj.getString("creationDate")
                );
                result.add(cost);
            }
        } catch (IOException e) {
            throw new CostManagerException(e.getMessage());
        } catch (InterruptedException e) {
            throw new CostManagerException(e.getMessage());
        }

        return result;
    }

    /**
     * Get cost by costId
     * @param costId
     * @return cost with the given costId
     * @throws CostManagerException
     */
    @Override
    public Cost getById(String costId) throws CostManagerException {

        // build the request
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/costs/" + costId))
                .GET()
                .build();

        // handle response
        Cost result;
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String stringResponse = response.body();
            JSONObject costObj = new JSONObject(stringResponse);
            result = new Cost(
                    costObj.getString("costId"),
                    costObj.getString("userId"),
                    costObj.getString("categoryId"),
                    costObj.getDouble("sum"),
                    costObj.getString("currencyId"),
                    costObj.getString("description"),
                    costObj.getString("creationDate")
            );
        } catch (IOException e) {
            throw new CostManagerException(e.getMessage());
        } catch (InterruptedException e) {
            throw new CostManagerException(e.getMessage());
        }

        return result;
    }

    /**
     * Add new cost
     * @param obj - cost object
     * @throws CostManagerException
     */
    @Override
    public void add(Cost obj) throws CostManagerException {

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
                .uri(URI.create(api_url + "/costs"))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // handle response
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new CostManagerException(e.getMessage());
        } catch (InterruptedException e) {
            throw new CostManagerException(e.getMessage());
        }
    }

    /**
     * Delete a cost with the given costId
     * @param costId
     * @throws CostManagerException
     */
    public void delete(String costId) throws CostManagerException {

        // build the request
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/costs/" + costId))
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
