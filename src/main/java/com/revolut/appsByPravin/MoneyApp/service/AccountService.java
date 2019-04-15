package com.revolut.appsByPravin.MoneyApp.service;

import com.revolut.appsByPravin.MoneyApp.model.Account;
import com.revolut.appsByPravin.MoneyApp.model.User;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> getAllAccounts(Long aLong) ;
}
