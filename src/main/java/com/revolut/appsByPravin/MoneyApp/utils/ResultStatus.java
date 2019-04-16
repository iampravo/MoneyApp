package com.revolut.appsByPravin.MoneyApp.utils;

public enum ResultStatus {
    SUCCESS("Success"), ERROR("Error");

    private String status;

    ResultStatus(final String status) {
        this.status = status;
    }

}
