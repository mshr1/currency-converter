package com.mshr;

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
            var result = client.getCurrencies();
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("Ошибка при запросе: " + e.getMessage());
        }
    }

}
