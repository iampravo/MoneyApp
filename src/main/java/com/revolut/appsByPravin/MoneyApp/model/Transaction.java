package com.revolut.appsByPravin.MoneyApp.model;

import com.revolut.appsByPravin.MoneyApp.data.Currency;
import com.revolut.appsByPravin.MoneyApp.data.TransactionStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;

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

    public void setTransactionId(final long transactionId) {
        this.transactionId = transactionId;
    }

    public long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(final long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public long getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(final long toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public void setUpdateDate(final Timestamp updateDate) {
        this.updateDate = updateDate;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(final String comments) {
        this.comments = comments;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(final TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public static Transaction newTransaction(final long fromAccountNumber, final long toAccountNumber, final BigDecimal amount, final Timestamp now) {
        final Transaction transaction = new Transaction();
        transaction.fromAccountNumber = fromAccountNumber;
        transaction.toAccountNumber = toAccountNumber;
        transaction.amount = amount;
        transaction.creationDate = now;
        transaction.updateDate = now;
        transaction.transactionStatus = TransactionStatus.SCHEDULED;
        transaction.comments = "Initiated";
        return transaction;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", fromAccountNumber=" + fromAccountNumber +
                ", toAccountNumber=" + toAccountNumber +
                ", amount=" + amount +
                ", currency=" + currency +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", transactionStatus=" + transactionStatus +
                ", comments='" + comments + '\'' +
                '}';
    }
}
