package com.revolut.appsByPravin.MoneyApp.dao;

import com.revolut.appsByPravin.MoneyApp.db.H2Database;
import com.revolut.appsByPravin.MoneyApp.exception.EntityNotFoundException;
import com.revolut.appsByPravin.MoneyApp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements BaseDao<User> {
    private final Logger log = LoggerFactory.getLogger(UserDao.class);

    private static final UserDao userDao = new UserDao();
    private UserDao() {
    }
    public static UserDao getInstance() {
        return userDao;
    }

    private static final String GET_ALL_USERS = " select * from bank_user ";
    private static final String GET_USER_BY_ID = "select user_id, user_name from bank_user where user_id = ?";

    public List<User> getAll() {
        log.info("Started method = getAll, class = UserDao");
        final List<User> users = new ArrayList<>();
        try (final Connection connection = H2Database.getConnection(); final PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS)) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final User user = new User();
                user.setUserId(resultSet.getLong("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                users.add(user);
            }
        } catch (final SQLException e) {
            log.error(e.getMessage());
            throw new EntityNotFoundException("Internal server exception.");
        }
        return users;
    }

    @Override
    public Optional<User> getById(final long userId) {
        log.info("Started method = getById, class = UserDao");
        try (final Connection connection = H2Database.getConnection(); final PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)) {
            statement.setLong(1, userId);
            try (final ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    final User user = new User();
                    user.setUserId(userId);
                    user.setUserName(resultSet.getString("user_name"));
                    return Optional.of(user);
                } else {
                    return Optional.empty();
                }
            }
        } catch (final SQLException e) {
            log.error(e.getMessage());
            throw new EntityNotFoundException("Internal server exception.");
        }
    }

    @Override
    public Optional<User> getById(final long id, final Connection connection) {
        return Optional.empty();
    }

    @Override
    public void save(final User user) {

    }

    @Override
    public void save(final User user, final Connection connection) {

    }

    @Override
    public void update(final User user, final String[] params) {

    }

    @Override
    public void update(final User user, final Connection connection) {

    }


    @Override
    public List<User> getAll(final long id) {
        return null;
    }

}
