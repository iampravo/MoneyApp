package com.revolut.appsByPravin.MoneyApp.dao;

import com.revolut.appsByPravin.MoneyApp.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getAllUsers() throws SQLException;

    Optional<User> getUserById(Long userId);
}
