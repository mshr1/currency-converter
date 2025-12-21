package com.mshr.model;

public class ConversionResult {
  private double amount;
  private String fromCurrency;
  private String toCurrency;
  private double convertedAmount;
  private String date;

  @Override
  public String toString() {

    return String.format("%.2f %s = %.2f %s (%s)",
        amount, fromCurrency, convertedAmount, toCurrency, date);
  }

  ConversionResult(double amount, String fromCurrency, String toCurrency, double convertedAmount, String date);
};
