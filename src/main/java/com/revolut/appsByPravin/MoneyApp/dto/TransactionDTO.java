package com.revolut.appsByPravin.MoneyApp.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDTO {
    private long fromAccountNumber;
    private long toAccountNumber;
    private BigDecimal amount;
    private String currency;

    public TransactionDTO() {
    }

    public long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public long getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(long toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
