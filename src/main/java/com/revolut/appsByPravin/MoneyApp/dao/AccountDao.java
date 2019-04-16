package com.revolut.appsByPravin.MoneyApp.dao;

import com.revolut.appsByPravin.MoneyApp.db.H2Database;
import com.revolut.appsByPravin.MoneyApp.exception.EntityNotFoundException;
import com.revolut.appsByPravin.MoneyApp.exception.TransactionException;
import com.revolut.appsByPravin.MoneyApp.model.Account;
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

    private static final AccountDao accountDao = new AccountDao();
    private AccountDao() {
    }
    public static AccountDao getInstance() {
        return accountDao;
    }

    @Override
    public List<Account> getAll(final long id) {
        log.info("Started method = getAll, class = AccountDao");
        final List<Account> accounts = new ArrayList<>();
        try (final Connection connection = H2Database.getConnection();
             final PreparedStatement statement = connection.prepareStatement(GET_ALL_ACCOUNTS_BY_USER_ID)) {
            statement.setLong(1, id);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Account account = new Account();
                account.setAccountNumber(resultSet.getLong("account_number"));
                account.setAccountType(resultSet.getString("account_type"));
                account.setBalance(resultSet.getBigDecimal("balance"));
                account.setLocalCurrency(resultSet.getString("currency"));
                accounts.add(account);
            }
            statement.close();
        } catch (final SQLException e) {
            log.error(e.getMessage());
            throw new EntityNotFoundException("Internal Server Error.");
        }
        return accounts;
    }


    public Optional<Account> getById(final long id, final Connection connection) {
        try (final PreparedStatement statement = connection.prepareStatement(SELECT_ACCOUNT_FOR_UPDATE)) {
            statement.setLong(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                return rs.next() ? Optional.of(new Account(
                        id,
                        rs.getString("account_type"),
                        rs.getBigDecimal("balance"),
                        rs.getString("currency"))
                ) : Optional.empty();
            }
        } catch (final SQLException e) {
            log.error(e.getMessage());
            throw new EntityNotFoundException("Internal Server Error.");
        }
    }

    @Override
    public void update(final Account account, final Connection connection) {
        try (final PreparedStatement ps = connection.prepareStatement(UPDATE_BALANCE)) {
            ps.setBigDecimal(1, account.getBalance());
            ps.setLong(2, account.getAccountNumber());
            if (ps.executeUpdate() == 0) {
                log.error("Unable to update account(account_number={}) balance to a value: {}",
                        account.getAccountNumber(), account.getBalance());
                throw new TransactionException("Failed to update account balance");
            }
        } catch (final SQLException e) {
            log.error(e.getMessage());
            throw new TransactionException("Internal Server Error.");
        }

    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public Optional<Account> getById(final long id) {
        return Optional.empty();
    }

    @Override
    public void save(final Account account) {

    }

    @Override
    public void save(final Account account, final Connection connection) {

    }

    @Override
    public void update(final Account account, final String[] params) {

    }


}
