package com.revolut.appsByPravin.MoneyApp.exception;

import java.net.HttpURLConnection;

public class MalformedRequestException extends ApiException {
    static final long serialVersionUID = -7034897190745768939L;


    public MalformedRequestException(final String message) {
        super(message,HttpURLConnection.HTTP_BAD_REQUEST);
    }
}
