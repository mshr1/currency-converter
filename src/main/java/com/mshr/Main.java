package com.mshr;

import java.util.Map;

import com.mshr.client.FrankfurterClient;

/**
 * Hello world!
 *
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Currency Converter v1.0");

        FrankfurterClient client = new FrankfurterClient();
        try {
            Map<String, String> currencies = client.getCurrencies();
            System.out.println("Available currencies: \n");

            currencies.forEach((code, name) -> System.out.println(code + " - " + name));

        } catch (Exception e) {
            System.err.println("Ошибка при запросе: " + e.getMessage());
        }
    }

}
