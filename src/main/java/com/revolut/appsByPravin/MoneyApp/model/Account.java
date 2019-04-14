package com.revolut.appsByPravin.MoneyApp.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    private long accountNumber;
    private String accountType;
    private BigDecimal balance;
    private String localCurrency;

    public Account(long accountNumber, String accountType, BigDecimal balance, String localCurrency) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.localCurrency = localCurrency;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getLocalCurrency() {
        return localCurrency;
    }

    public void setLocalCurrency(String localCurrency) {
        this.localCurrency = localCurrency;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
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
