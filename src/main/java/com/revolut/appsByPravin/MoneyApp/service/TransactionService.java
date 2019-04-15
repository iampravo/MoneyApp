package com.revolut.appsByPravin.MoneyApp.service;

import com.revolut.appsByPravin.MoneyApp.dto.TransactionDTO;
import com.revolut.appsByPravin.MoneyApp.model.Transaction;
import com.revolut.appsByPravin.MoneyApp.model.User;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Optional<Transaction> transfer(TransactionDTO transferDTO);

    List<Transaction> getAllTransactions();
}

