package com.revolut.appsByPravin.MoneyApp.data;


public enum TransactionStatus {
    SCHEDULED("Scheduled"),
    IN_PROGRESS("In Progress"),
    FAILED("Failed"),
    PASSED("Passed");


    private String status;

    TransactionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}