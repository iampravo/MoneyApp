package com.revolut.appsByPravin.MoneyApp.dao;

import com.revolut.appsByPravin.MoneyApp.db.H2Database;
import com.revolut.appsByPravin.MoneyApp.exception.EntityNotFoundException;
import com.revolut.appsByPravin.MoneyApp.exception.TransactionException;
import com.revolut.appsByPravin.MoneyApp.model.Account;
import com.revolut.appsByPravin.MoneyApp.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDao implements BaseDao<Account> {
    private final Logger log = LoggerFactory.getLogger(AccountDao.class);
    private static final String GET_ALL_ACCOUNTS_BY_USER_ID = " select * from bank_account where user_id = ?";
    private static final String SELECT_ACCOUNT_FOR_UPDATE = "select account_type, balance, currency from bank_account where account_number = ? for update";
    private static final String UPDATE_BALANCE = "update bank_account set balance = ? where account_number = ?";


    @Override
    public List<Account> getAll(long id) {
        log.info("Started method = getAll, class = AccountDao");
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = H2Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_ACCOUNTS_BY_USER_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                account.setAccountNumber(resultSet.getLong("account_number"));
                account.setAccountType(resultSet.getString("account_type"));
                account.setBalance(resultSet.getBigDecimal("balance"));
                account.setLocalCurrency(resultSet.getString("currency"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new EntityNotFoundException("Internal Server Error.");
        }
        return accounts;
    }


    public Optional<Account> getById(long id, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ACCOUNT_FOR_UPDATE)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Account(
                            id,
                            rs.getString("account_type"),
                            rs.getBigDecimal("balance"),
                            rs.getString("currency"))
                    );
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new EntityNotFoundException("Internal Server Error.");
        }
    }

    @Override
    public void update(Account account, Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_BALANCE)) {
            ps.setBigDecimal(1, account.getBalance());
            ps.setLong(2, account.getAccountNumber());
            if (ps.executeUpdate() == 0) {
                log.error("Unable to update account(account_number={}) balance to a value: {}",
                        account.getAccountNumber(), account.getBalance());
                throw new TransactionException("Failed to update account balance");
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new TransactionException("Internal Server Error.");
        }

    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public Optional<Account> getById(long id) {
        return Optional.empty();
    }

    @Override
    public void save(Account account) {

    }

    @Override
    public void save(Account account, Connection connection) {

    }

    @Override
    public void update(Account account, String[] params) {

    }


}
