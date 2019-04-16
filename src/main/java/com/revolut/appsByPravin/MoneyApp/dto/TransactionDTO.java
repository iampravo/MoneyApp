package com.revolut.appsByPravin.MoneyApp.dto;

import java.math.BigDecimal;

public class TransactionDTO {
    private long fromAccountNumber;
    private long toAccountNumber;
    private BigDecimal amount;
    private String currency;

    public long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public long getToAccountNumber() {
        return toAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "fromAccountNumber=" + fromAccountNumber +
                ", toAccountNumber=" + toAccountNumber +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
