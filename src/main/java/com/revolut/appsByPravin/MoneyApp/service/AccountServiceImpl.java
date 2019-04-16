package com.revolut.appsByPravin.MoneyApp.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.revolut.appsByPravin.MoneyApp.controller.AccountController;
import com.revolut.appsByPravin.MoneyApp.dao.AccountDao;
import com.revolut.appsByPravin.MoneyApp.dao.BaseDao;
import com.revolut.appsByPravin.MoneyApp.model.Account;
import com.revolut.appsByPravin.MoneyApp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {


    private static final AccountServiceImpl accountService = new AccountServiceImpl(AccountDao.getInstance());
    public static AccountServiceImpl getInstance() {
        return accountService;
    }
    private  AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    private AccountDao accountDao;// = new AccountDao();


    @Override
    public List<Account> getAllAccounts(Long userId) {
        log.info("Started method = getAllAccounts, class = AccountServiceImpl");
        return accountDao.getAll(userId);
    }

}
