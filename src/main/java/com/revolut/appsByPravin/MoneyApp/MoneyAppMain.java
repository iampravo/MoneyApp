package com.revolut.appsByPravin.MoneyApp;

import com.revolut.appsByPravin.MoneyApp.route.RouteController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.port;

public class MoneyAppMain {
    private final static Logger log = LoggerFactory.getLogger(MoneyAppMain.class);

    public static void main(String[] args) {

        log.info("Starting Application");
        port(8182);
        RouteController.initializeRoutes();
        log.info("Application has started successfully");
    }
}
