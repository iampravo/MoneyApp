package com.revolut.appsByPravin.MoneyApp.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {
    List<T> getAll();

    List<T> getAll(long id);

    Optional<T> getById(long id);

    Optional<T> getById(long id, Connection connection);

    void save(T t);

    void save(T t, Connection connection);

    void update(T t, String[] params);

    void update(T t, Connection connection);

}
