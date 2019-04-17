package com.revolut.appsByPravin.MoneyApp.dao;

import com.revolut.appsByPravin.MoneyApp.data.Currency;
import com.revolut.appsByPravin.MoneyApp.data.TransactionStatus;
import com.revolut.appsByPravin.MoneyApp.db.H2Database;
import com.revolut.appsByPravin.MoneyApp.dto.TransactionDTO;
import com.revolut.appsByPravin.MoneyApp.exception.ApiException;
import com.revolut.appsByPravin.MoneyApp.exception.EntityNotFoundException;
import com.revolut.appsByPravin.MoneyApp.exception.TransactionException;
import com.revolut.appsByPravin.MoneyApp.model.Account;
import com.revolut.appsByPravin.MoneyApp.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionDao implements BaseDao<Transaction> {
    private final Logger log = LoggerFactory.getLogger(TransactionDao.class);

    private static final TransactionDao transactionDao = new TransactionDao(AccountDao.getInstance());

    public static TransactionDao getInstance() {
        return transactionDao;
    }

    private TransactionDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    private AccountDao accountDao;
    private static final String GET_ALL_TRANS = "select * from user_transaction";
    private static final String LOG_TRANSACTION =
            " insert into user_transaction (from_account_number,to_account_number,amount,currency,creation_date,update_date,transaction_status,comments)" +
                    "values (?,?,?,?,?,?,?,?)";


    public Optional<Transaction> transfer(TransactionDTO transactionDTO) {
        log.info("Started method = transfer, class = TransactionDao");

        long fromAccountNumber = transactionDTO.getFromAccountNumber();
        long toAccountNumber = transactionDTO.getToAccountNumber();

        Transaction transaction = Transaction.newTransaction(fromAccountNumber, toAccountNumber, transactionDTO.getAmount(), Timestamp.from(Instant.now()));
        transaction.setCurrency(Currency.get(transactionDTO.getCurrency()));

        try (Connection connection = H2Database.getConnection()) {
            connection.setAutoCommit(false);
            try {
                save(transaction, connection);

                Optional<Account> fromAccount = accountDao.getById(fromAccountNumber, connection);

                if (!fromAccount.isPresent()) {
                    throw new TransactionException("Source account with account number " + fromAccountNumber + " does not exist");
                }
                Account fromAccountValue = fromAccount.get();

                if (fromAccountValue.getBalance().compareTo(transactionDTO.getAmount()) < 0) {
                    throw new TransactionException("Not enough balance in account with account number " + fromAccountNumber + "." +
                            "Balance is " + fromAccountValue.getBalance() + ". Required: " + transactionDTO.getAmount());
                }


                Optional<Account> toAccount = accountDao.getById(toAccountNumber, connection);

                if (!toAccount.isPresent()) {
                    throw new TransactionException("Destination account with account number " + fromAccountNumber + " does not exist");
                }
                Account toAccountValue = toAccount.get();

                if (!fromAccountValue.getLocalCurrency().equalsIgnoreCase(toAccountValue.getLocalCurrency())) {
                    throw new TransactionException("Cannot transfer money from account with currency: " + fromAccountValue.getLocalCurrency() +
                            " to account with currency: " + toAccountValue.getLocalCurrency());
                }
                
                fromAccountValue.withdraw(transactionDTO.getAmount());
                toAccountValue.deposit(transactionDTO.getAmount());

                transaction.setTransactionStatus(TransactionStatus.IN_PROGRESS);
                save(transaction, connection);

                accountDao.update(fromAccountValue, connection);
                accountDao.update(toAccountValue, connection);

                transaction.setTransactionStatus(TransactionStatus.PASSED);
                save(transaction, connection);
            } catch (ApiException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                transaction.setTransactionStatus(TransactionStatus.FAILED);
                transaction.setComments(e.getMessage());
                save(transaction);
                log.error("Transaction could be not completed " + transactionDTO.toString(), e.getMessage());
                throw new TransactionException(e.getMessage());
            }
            connection.commit();
            connection.setAutoCommit(true);
            return Optional.of(transaction);
        } catch (SQLException e) {
            log.error("Transaction could be not completed " + transactionDTO.toString(), e.getMessage());
            throw new TransactionException("Internal Server Error");
        }
    }


    @Override
    public void save(Transaction transaction) {
        try (Connection connection = H2Database.getConnection(); PreparedStatement stmt = connection.prepareStatement(LOG_TRANSACTION)) {
            stmt.setLong(1, transaction.getFromAccountNumber());
            stmt.setLong(2, transaction.getToAccountNumber());
            stmt.setBigDecimal(3, transaction.getAmount());
            stmt.setString(4, transaction.getCurrency().getCurrencyCode());
            stmt.setTimestamp(5, transaction.getCreationDate());
            stmt.setTimestamp(6, Timestamp.from(Instant.now()));
            stmt.setString(7, transaction.getTransactionStatus().getStatus());
            stmt.setString(8, transaction.getComments());
            stmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error While saving Transaction Object" + transaction.toString(), e.getMessage());
            throw new TransactionException("Internal Server Error.");
        }
    }

    @Override
    public void save(Transaction transaction, Connection connection) {
        try (PreparedStatement stmt = connection.prepareStatement(LOG_TRANSACTION)) {
            stmt.setLong(1, transaction.getFromAccountNumber());
            stmt.setLong(2, transaction.getToAccountNumber());
            stmt.setBigDecimal(3, transaction.getAmount());
            stmt.setString(4, transaction.getCurrency().getCurrencyCode());
            stmt.setTimestamp(5, transaction.getCreationDate());
            stmt.setTimestamp(6, Timestamp.from(Instant.now()));
            stmt.setString(7, transaction.getTransactionStatus().getStatus());
            stmt.setString(8, transaction.getComments());
            stmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error While saving Transaction Object" + transaction.toString(), e.getMessage());
            throw new TransactionException("Internal Server Error.");
        }
    }


    @Override
    public List<Transaction> getAll() {
        log.info("Started method = getAll, class = TransactionDao");
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = H2Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_TRANS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(resultSet.getLong("transaction_id"));
                transaction.setFromAccountNumber(resultSet.getLong("from_account_number"));
                transaction.setToAccountNumber(resultSet.getLong("to_account_number"));
                transaction.setAmount(resultSet.getBigDecimal("amount"));
                transaction.setCurrency(Currency.get(resultSet.getString("currency")));
                transaction.setCreationDate(resultSet.getTimestamp("creation_date"));
                transaction.setUpdateDate(resultSet.getTimestamp("update_date"));
                transaction.setTransactionStatus(TransactionStatus.get(resultSet.getString("transaction_status")));
                transaction.setComments(resultSet.getString("comments"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new EntityNotFoundException("Internal Server Error.");
        }
        return transactions;
    }

    @Override
    public List<Transaction> getAll(long id) {
        return null;
    }

    @Override
    public Optional<Transaction> getById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Transaction> getById(long id, Connection connection) {
        return Optional.empty();
    }

    @Override
    public void update(Transaction transaction, String[] params) {

    }

    @Override
    public void update(Transaction transaction, Connection connection) {

    }

}
