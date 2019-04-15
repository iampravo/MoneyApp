package com.revolut.appsByPravin.MoneyApp.service;

import com.revolut.appsByPravin.MoneyApp.controller.TransactionController;
import com.revolut.appsByPravin.MoneyApp.dao.TransactionDao;
import com.revolut.appsByPravin.MoneyApp.dto.TransactionDTO;
import com.revolut.appsByPravin.MoneyApp.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class TransactionServiceImpl implements TransactionService {
    private final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private TransactionDao transactionDao = new TransactionDao();

    @Override
    public Optional<Transaction> transfer(TransactionDTO transactionDTO) {
        log.info("Started method = transfer, class = TransactionServiceImpl");
        return transactionDao.transfer(transactionDTO);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        log.info("Started method = getAllTransactions, class = TransactionServiceImpl");
        return transactionDao.getAll();
    }
}