package com.revolut.appsByPravin.MoneyApp.utils;

public enum ResultStatus {
    SUCCESS("Success"), ERROR("Error");

    private String status;

    ResultStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
