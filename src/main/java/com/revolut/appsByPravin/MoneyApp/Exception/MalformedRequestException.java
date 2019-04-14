package com.revolut.appsByPravin.MoneyApp.Exception;

import java.net.HttpURLConnection;

public class MalformedRequestException extends ApiException {

    public MalformedRequestException(String message) {
        super(message,HttpURLConnection.HTTP_BAD_REQUEST);
    }
}
