package com.revolut.appsByPravin.MoneyApp.model;

import com.google.gson.JsonElement;
import com.revolut.appsByPravin.MoneyApp.utils.ResultStatus;

public class ResponseMapper {
    private ResultStatus resultStatus;
    private String message;
    private JsonElement jsonElement;

    public ResponseMapper() {
    }

    public ResponseMapper(ResultStatus resultStatus, String message) {
        this.resultStatus = resultStatus;
        this.message = message;
    }

    public ResponseMapper(ResultStatus resultStatus, String message, JsonElement jsonElement) {
        this.resultStatus = resultStatus;
        this.message = message;
        this.jsonElement = jsonElement;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonElement getJsonElement() {
        return jsonElement;
    }

    public void setJsonElement(JsonElement jsonElement) {
        this.jsonElement = jsonElement;
    }
}
