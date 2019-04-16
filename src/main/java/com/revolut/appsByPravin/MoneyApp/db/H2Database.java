package com.revolut.appsByPravin.MoneyApp.db;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class H2Database {
    private static final Logger log = LoggerFactory.getLogger(H2Database.class);
    private static HikariDataSource dataSource;
    //Setting Up initializing H2 database and initialize it by the schema with test data data

    public static void initializeDatabase() {
        log.info("Started method = initializeDatabase, class = H2Database");
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:mem:test;" +
                "INIT=RUNSCRIPT FROM 'classpath:db/database_schema.sql'\\;RUNSCRIPT FROM 'classpath:db/initial_data.sql';" +
                "TRACE_LEVEL_FILE=4");
        //TODO login and password should be provided trough system variables
        dataSource.setUsername("username");
        dataSource.setPassword("password");
        log.info("Ended method = initializeDatabase, class = H2Database");
    }

    private H2Database() {
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
