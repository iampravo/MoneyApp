package com.revolut.appsByPravin.MoneyApp.model;

import java.util.List;
import java.util.Objects;

public class User {
    private long userId;
    private String userName;
    private List<Account> accounts;

    public User() {

    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public void setUserId(final long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", accounts=" + accounts +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return userId == user.userId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId);
    }
}
