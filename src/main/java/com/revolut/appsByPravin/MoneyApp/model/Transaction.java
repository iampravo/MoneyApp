package com.revolut.appsByPravin.MoneyApp.model;

import com.revolut.appsByPravin.MoneyApp.data.Currency;
import com.revolut.appsByPravin.MoneyApp.data.TransactionStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

public class Transaction {
    private long transactionId;
    private long fromAccountNumber;
    private long toAccountNumber;
    private BigDecimal amount;
    private Currency currency;
    private Timestamp creationDate;
    private Timestamp updateDate;
    private TransactionStatus transactionStatus;
    private String comments;

    public Transaction() {
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
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

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public static Transaction newTransaction(long fromAccountNumber, long toAccountNumber, BigDecimal amount, Timestamp now) {
        Transaction transaction = new Transaction();
        transaction.setFromAccountNumber(fromAccountNumber);
        transaction.setToAccountNumber(toAccountNumber);
        transaction.setAmount(amount);
        transaction.setCreationDate(now);
        transaction.setUpdateDate(now);
        transaction.setTransactionStatus(TransactionStatus.SCHEDULED);
        transaction.setComments("Initiated");
        return transaction;
    }
}
