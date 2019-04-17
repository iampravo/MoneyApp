package com.revolut.appsByPravin.MoneyApp.exception;

import java.net.HttpURLConnection;

public class EntityNotFoundException extends ApiException {
    static final long serialVersionUID = -7034897190746766939L;

    public EntityNotFoundException(final String message) {
        super(message, HttpURLConnection.HTTP_NOT_FOUND);
    }
}
