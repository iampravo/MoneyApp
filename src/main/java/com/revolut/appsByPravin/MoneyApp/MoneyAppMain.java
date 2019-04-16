package com.revolut.appsByPravin.MoneyApp;

import com.google.inject.Injector;
import com.revolut.appsByPravin.MoneyApp.db.H2Database;
import com.revolut.appsByPravin.MoneyApp.route.RouteController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import spark.servlet.SparkApplication;

import static spark.Spark.port;

public class MoneyAppMain {
    private final static Logger log = LoggerFactory.getLogger(MoneyAppMain.class);

    @Inject
    private static RouteController routeController;

    public static void main(String[] args) {
        log.info("Starting Application");
        port(8182);
        RouteController.initializeRoutes();
        H2Database.initializeDatabase();
        log.info("Application has started successfully");
    }
}
