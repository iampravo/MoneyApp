package com.revolut.appsByPravin.MoneyApp.data;


import java.util.HashMap;
import java.util.Map;

public enum Currency {
    US_DOLLAR("USD"),
    RUPEE("INR"),
    POUND("GBP");

    private static final Map<String, Currency> lookup = new HashMap();

    private String currencyCode;

    static {
        for (Currency c : Currency.values()) {
            lookup.put(c.getCurrencyCode(), c);
        }
    }

    Currency(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public static Currency get(String value) {
        return lookup.get(value);
    }
}