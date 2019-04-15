package com.revolut.appsByPravin.MoneyApp.exception;

public class ApiException extends RuntimeException {
    static final long serialVersionUID = -9034897190746766939L;

    private int httpURLConnection;

    public ApiException(String message, int httpURLConnection) {
        super(message);
        this.httpURLConnection = httpURLConnection;
    }

    public int getHttpURLConnection() {
        return httpURLConnection;
    }
}
