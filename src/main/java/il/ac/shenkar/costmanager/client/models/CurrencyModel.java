package il.ac.shenkar.costmanager.client.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import il.ac.shenkar.costmanager.CostManagerException;
import il.ac.shenkar.costmanager.entities.Currency;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.http.HttpHeaders;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyModel implements IModel<Currency> {

    @Override
    public List<Currency> getAll() throws CostManagerException {

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/currencies"))
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
    public Currency getById(String currencyId) throws CostManagerException {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/currencies/" + currencyId))
                .GET()
                .build();

        Currency result = new Currency();
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
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void add(Currency obj) throws CostManagerException, JsonProcessingException {

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(obj);

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api_url + "/currencies"))
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
