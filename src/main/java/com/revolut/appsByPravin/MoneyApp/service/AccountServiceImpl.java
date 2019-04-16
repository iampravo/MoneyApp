package com.revolut.appsByPravin.MoneyApp.service;

import com.revolut.appsByPravin.MoneyApp.dao.AccountDao;
import com.revolut.appsByPravin.MoneyApp.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AccountServiceImpl implements AccountService {


    private static final AccountServiceImpl accountService = new AccountServiceImpl(AccountDao.getInstance());
    public static AccountServiceImpl getInstance() {
        return accountService;
    }
    private  AccountServiceImpl(final AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    private AccountDao accountDao;// = new AccountDao();


    @Override
    public List<Account> getAllAccounts(final Long userId) {
        log.info("Started method = getAllAccounts, class = AccountServiceImpl");
        return accountDao.getAll(userId);
    }

}
