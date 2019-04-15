package com.revolut.appsByPravin.MoneyApp.service;

import com.revolut.appsByPravin.MoneyApp.dao.UserDao;
import com.revolut.appsByPravin.MoneyApp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private UserDao userDao = new UserDao();

    public List<User> getAllUsers() {
        log.info("Started method = getAllUsers, class = UserServiceImpl");
        return userDao.getAll();
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        log.info("Started method = getUserById, class = UserServiceImpl");

        return userDao.getById(userId);
    }
}
