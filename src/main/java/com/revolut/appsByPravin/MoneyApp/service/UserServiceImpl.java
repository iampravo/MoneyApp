package com.revolut.appsByPravin.MoneyApp.service;

import com.revolut.appsByPravin.MoneyApp.dao.UserDao;
import com.revolut.appsByPravin.MoneyApp.dao.UserDaoImpl;
import com.revolut.appsByPravin.MoneyApp.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userDao.getUserById(userId);
    }
}
