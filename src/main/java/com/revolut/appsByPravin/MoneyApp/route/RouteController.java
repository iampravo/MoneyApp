package com.revolut.appsByPravin.MoneyApp.route;

import com.google.gson.Gson;
import com.revolut.appsByPravin.MoneyApp.controller.AccountController;
import com.revolut.appsByPravin.MoneyApp.controller.TransactionController;
import com.revolut.appsByPravin.MoneyApp.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.www.protocol.http.HttpURLConnection;

import static spark.Spark.*;

public class RouteController {

    private static final RouteController routeController = new RouteController(AccountController.getInstance(), UserController.getInstance(), TransactionController.getInstance());

    private RouteController(final AccountController accountController, final UserController userController, final TransactionController transactionController) {
        this.accountController = accountController;
        this.userController = userController;
        this.transactionController = transactionController;
    }

    private AccountController accountController;
    private UserController userController;
    private TransactionController transactionController;
    private final Logger log = LoggerFactory.getLogger(RouteController.class);


    public static void initializeRoutes() {
        routeController.initialize();
    }

    private void initialize() {

        path("/moneyapp/v1", () -> {
            before("/*", (request, response) -> {
                //@todo Auth can be done before proceeding ahead
                log.info("Api call received");
            });
            path("/users", () -> {
                get("", (request, response) -> new Gson().toJson(userController.getAllUsers(request, response)));
                get("/:userId", (request, response) -> new Gson().toJson(userController.getUserById(request, response)));
                path("/:userId", () -> get("/accounts", (request, response) -> new Gson().toJson(accountController.getAllAccounts(request, response))
                ));
            });
            post("/transfer", (request, response) -> new Gson().toJson(transactionController.transfer(request, response)));
            get("/transactions", (request, response) -> {
                return new Gson().toJson(transactionController.getAllTransactions(request, response));
            });
        });

        notFound((request, response) -> {
            log.info("Unknown API path.");
            response.status(HttpURLConnection.HTTP_NOT_FOUND);
            return new Gson().toJson("Please provide the correct Api path.");
        });
        exception(Exception.class, (e, request, response) -> {
            log.info("Internal server error.");
            response.status(HttpURLConnection.HTTP_INTERNAL_ERROR);
        });
    }
}
