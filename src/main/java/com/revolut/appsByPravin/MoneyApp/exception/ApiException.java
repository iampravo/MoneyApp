package com.revolut.appsByPravin.MoneyApp.exception;

import java.io.NotSerializableException;

public class ApiException extends RuntimeException {
    static final long serialVersionUID = -9034897190746766939L;

    private int httpURLConnection;

    public ApiException(final String message, final int httpURLConnection) {
        super(message);
        this.httpURLConnection = httpURLConnection;
    }

    public int getHttpURLConnection() {
        return httpURLConnection;
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        throw new NotSerializableException("com.revolut.appsByPravin.MoneyApp.exception.ApiException");
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        throw new NotSerializableException("com.revolut.appsByPravin.MoneyApp.exception.ApiException");
    }
}
