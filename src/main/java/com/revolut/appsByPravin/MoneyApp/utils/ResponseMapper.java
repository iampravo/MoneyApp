package com.revolut.appsByPravin.MoneyApp.utils;

import com.google.gson.JsonElement;

public class ResponseMapper {
    private ResultStatus resultStatus;
    private String message;
    private JsonElement jsonElement;

    public ResponseMapper(final ResultStatus resultStatus, final String message) {
        this.resultStatus = resultStatus;
        this.message = message;
    }

    public ResponseMapper(final ResultStatus resultStatus, final String message, final JsonElement jsonElement) {
        this.resultStatus = resultStatus;
        this.message = message;
        this.jsonElement = jsonElement;
    }

}
