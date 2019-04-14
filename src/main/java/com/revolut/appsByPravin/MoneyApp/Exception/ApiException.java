package com.revolut.appsByPravin.MoneyApp.Exception;

public class ApiException extends RuntimeException {

    private int httpURLConnection;

    public ApiException(String message, int httpURLConnection){
        super(message);
        this.httpURLConnection = httpURLConnection;
    }

    public int getHttpURLConnection() {
        return httpURLConnection;
    }

    public void setHttpURLConnection(int httpURLConnection) {
        this.httpURLConnection = httpURLConnection;
    }
}
