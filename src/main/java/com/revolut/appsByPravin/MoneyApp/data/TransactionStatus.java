package com.revolut.appsByPravin.MoneyApp.data;


import com.revolut.appsByPravin.MoneyApp.model.Transaction;

import java.util.HashMap;
import java.util.Map;

public enum TransactionStatus {
    SCHEDULED("Scheduled"),
    IN_PROGRESS("In Progress"),
    FAILED("Failed"),
    PASSED("Passed");

    private static final Map<String, TransactionStatus> lookup = new HashMap();

    private String status;

    static {
        for (TransactionStatus c : TransactionStatus.values()) {
            lookup.put(c.getStatus(), c);
        }
    }


    TransactionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public static TransactionStatus get(String value) {
        return lookup.get(value);
    }
}