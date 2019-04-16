package com.revolut.appsByPravin.MoneyApp.exception;

import java.net.HttpURLConnection;

public class TransactionException extends ApiException {
    static final long serialVersionUID = -7034897190126766939L;

    public TransactionException(final String message) {
        super(message, HttpURLConnection.HTTP_INTERNAL_ERROR);
    }
}
