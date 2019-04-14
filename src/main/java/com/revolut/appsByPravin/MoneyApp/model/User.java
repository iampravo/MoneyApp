package com.revolut.appsByPravin.MoneyApp.model;

import java.util.List;
import java.util.Objects;

public class User {
    private long userId;
    private String userName;
    private List<Account> accounts;

    public User(String userName, long userId, List<Account> accounts) {

        this.userName = userName;
        this.userId = userId;
        this.accounts = accounts;
    }

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId);
    }
}
