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

public class CostModel implements IModel<Cost> {

    public List<Cost> getByUserId(String userId) throws CostManagerException {

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/costs/users/" + userId))
                .GET()
                .build();

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
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Cost> getAll() throws CostManagerException {

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/costs"))
                .GET()
                .build();

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
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Cost getById(String categoryId) throws CostManagerException {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/costs/" + categoryId))
                .GET()
                .build();

        Cost result = new Cost();
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
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void add(Cost obj) throws CostManagerException, JsonProcessingException {

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(obj);

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/costs"))
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
