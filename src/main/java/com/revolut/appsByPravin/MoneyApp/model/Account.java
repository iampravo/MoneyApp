package com.revolut.appsByPravin.MoneyApp.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    private long accountNumber;
    private String accountType;
    private BigDecimal balance;
    private String localCurrency;

    public Account() {
    }

    public Account(final long accountNumber, final String accountType, final BigDecimal balance, final String localCurrency) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.localCurrency = localCurrency;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(final long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }

    public String getLocalCurrency() {
        return localCurrency;
    }

    public void setLocalCurrency(final String localCurrency) {
        this.localCurrency = localCurrency;
    }

    public void withdraw(final BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    public void deposit(final BigDecimal amount) {
        balance = balance.add(amount);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Account account = (Account) o;
        return accountNumber == account.accountNumber;
    }

    @Override
    public int hashCode() {

        return Objects.hash(accountNumber);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", localCurrency='" + localCurrency + '\'' +
                '}';
    }
}
