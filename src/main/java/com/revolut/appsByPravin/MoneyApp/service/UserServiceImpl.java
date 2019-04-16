package com.revolut.appsByPravin.MoneyApp.service;


import com.revolut.appsByPravin.MoneyApp.dao.UserDao;
import com.revolut.appsByPravin.MoneyApp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserServiceImpl transactionService = new UserServiceImpl(UserDao.getInstance());

    public static UserServiceImpl getInstance() {
        return transactionService;
    }

    private UserServiceImpl(final UserDao userDao) {
        this.userDao = userDao;
    }

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserDao userDao;

    public List<User> getAllUsers() {
        log.info("Started method = getAllUsers, class = UserServiceImpl");
        return userDao.getAll();
    }

    @Override
    public Optional<User> getUserById(final Long userId) {
        log.info("Started method = getUserById, class = UserServiceImpl");

        return userDao.getById(userId);
    }
}
