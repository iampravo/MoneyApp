package com.revolut.appsByPravin.MoneyApp;

import com.revolut.appsByPravin.MoneyApp.db.H2Database;
import com.revolut.appsByPravin.MoneyApp.route.RouteController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Spark;

public class MoneyAppMain {
    private static final Logger log = LoggerFactory.getLogger(MoneyAppMain.class);

    private static RouteController routeController;

    public static void main(final String[] args) {
        log.info("Starting Application");
        Spark.port(8182);
        RouteController.initializeRoutes();
        H2Database.initializeDatabase();
        log.info("Application has started successfully");
    }
}
