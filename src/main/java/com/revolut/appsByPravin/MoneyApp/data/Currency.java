package com.revolut.appsByPravin.MoneyApp.data;


public enum Currency {
    US_DOLLAR("USD"),
    RUPEE("INR"),
    POUND("GBP");


    private String currencyCode;

    Currency(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    }