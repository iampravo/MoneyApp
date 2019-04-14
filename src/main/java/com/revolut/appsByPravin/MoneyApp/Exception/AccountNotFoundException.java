package com.revolut.appsByPravin.MoneyApp.Exception;

import java.net.HttpURLConnection;

public class AccountNotFoundException extends ApiException {
    public AccountNotFoundException(String message) {
        super(message, HttpURLConnection.HTTP_NO_CONTENT);
    }
}
