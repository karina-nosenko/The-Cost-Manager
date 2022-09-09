package il.ac.shenkar.costmanager.client.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Currency;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Class that enables CRUD actions with the currency entity.
 * Communicates with the server currency model.
 */
public class CurrencyModel implements IModel<Currency> {

    /**
     * Get all currencies
     * @return currencies list
     * @throws CostManagerException
     */
    @Override
    public List<Currency> getAll() throws CostManagerException {

        // build the request
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/currencies"))
                .GET()
                .build();

        // handle response
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
            throw new CostManagerException(e.getMessage());
        } catch (InterruptedException e) {
            throw new CostManagerException(e.getMessage());
        }

        return result;
    }

    /**
     * Get currency by currencyId
     * @param currencyId
     * @return currency with the given currencyId
     * @throws CostManagerException
     */
    @Override
    public Currency getById(String currencyId) throws CostManagerException {

        // build the request
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/currencies/" + currencyId))
                .GET()
                .build();

        // handle response
        Currency result;
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String stringResponse = response.body();
            JSONObject currencyObj = new JSONObject(stringResponse);
            result = new Currency(
                    currencyObj.getString("currencyId"),
                    currencyObj.getString("name"),
                    currencyObj.getDouble("rate")
            );
        } catch (IOException e) {
            throw new CostManagerException(e.getMessage());
        } catch (InterruptedException e) {
            throw new CostManagerException(e.getMessage());
        }

        return result;
    }

    /**
     * Add new currency
     * @param obj - currency object
     * @throws CostManagerException
     */
    @Override
    public void add(Currency obj) throws CostManagerException {

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
                .uri(URI.create(api_url + "/currencies"))
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
     * Delete a currency with the given currencyId
     * @param currencyId
     * @throws CostManagerException
     */
    public void delete(String currencyId) throws CostManagerException {

        // build the request
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/currencies/" + currencyId))
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
