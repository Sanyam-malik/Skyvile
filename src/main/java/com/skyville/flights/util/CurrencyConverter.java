package com.skyville.flights.util;

import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class CurrencyConverter {
    private final Map<String, Double> rates = Map.of(
            "USD", 1.0,
            "EUR", 1.12, // 1 EUR = 1.12 USD (example)
            "INR", 0.012 // 1 INR = 0.012 USD
    );

    public double toUsd(double amount, String currency) {
        double rate = rates.getOrDefault(currency.toUpperCase(), 1.0);
        return amount * rate;
    }

    public double convert(double amount, String fromCurrency, String toCurrency) {
        double usd = toUsd(amount, fromCurrency);
        double toRate = rates.getOrDefault(toCurrency.toUpperCase(), 1.0);
        return usd / toRate;
    }
}

