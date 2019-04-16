package com.revolut.appsByPravin.MoneyApp.data;


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
        for (final TransactionStatus c : TransactionStatus.values()) {
            lookup.put(c.status, c);
        }
    }


    TransactionStatus(final String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


    public static TransactionStatus get(final String value) {
        return lookup.get(value);
    }
}