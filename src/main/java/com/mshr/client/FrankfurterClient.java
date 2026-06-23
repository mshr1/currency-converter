package com.mshr.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mshr.model.ConversionResult;

import java.util.Map;

public class FrankfurterClient {
  private final HttpClient client = HttpClient.newHttpClient();

  public Map<String, String> getCurrencies() throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.frankfurter.dev/v1/currencies")).GET()
        .build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

    if (response.statusCode() != 200) {
      throw new IOException("Api error: status " + response.statusCode());
    }
    // Шаг 4: Получаем JSON-строку из ответа (это настоящий JSON от Frankfurter!)
    String json = response.body();
    // Парсим JSON в Map
    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> currencies = mapper.readValue(json, new TypeReference<Map<String, String>>() {
    });
    return currencies;
  }

  public ConversionResult convert(double amount, String from, String to)
      throws IOException, InterruptedException {
    String url = String.format("https://api.frankfurter.dev/latest?amount=%.2f&from=%s&to=%s",
        amount, from.toUpperCase(), to.toUpperCase());

    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

    // отправка запроса

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

    if (response.statusCode() != 200) {
      throw new IOException("Ошибка!");
    }

    // Распарс json

    ObjectMapper mapper = new ObjectMapper();

    Map<String, Object> jsonMap = mapper.readValue(response.body(), new TypeReference<Map<String, Object>>() {
    });

    String date = (String) jsonMap.get("date");

    Map<String, Double> rates = (Map<String, Double>) jsonMap.get("rates");

    double convertedValue = rates.get(to.toUpperCase());
    return new ConversionResult(amount, from.toUpperCase(), to.toUpperCase(), convertedValue, date);
  }
}