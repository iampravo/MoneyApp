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

    //@todo try to apply inject over here
    private AccountController accountController;
    private UserController userController;
    private TransactionController transactionController;
    private final Logger log = LoggerFactory.getLogger(RouteController.class);

    public RouteController(AccountController accountController, UserController userController, TransactionController transactionController) {
        this.accountController = accountController;
        this.userController = userController;
        this.transactionController = transactionController;
    }

    public static void initializeRoutes() {
        RouteController routeController = new RouteController(new AccountController(), new UserController(), new TransactionController());
        routeController.initialize();
    }

    private void initialize() {

        path("/moneyapp/v1", () -> {
            before("/*", (request, response) -> {
                //@todo Auth can be done before proceeding ahead
                log.info("Api call received");
            });
            path("/users", () -> {
                get("", (request, response) -> new Gson()
                        .toJson(userController.getAllUsers(request, response)));
                path("/:userId", () -> {
                    get("", (request, response) -> new Gson().toJson(userController.getUserById(request, response)));
                    get("/accounts", (request, response) ->
                            new Gson().toJson(accountController.getAllAccounts(request, response))
                    );
                    get("/accounts/:accountNumber", (request, response) ->
                            new Gson().toJson(accountController.getAccountById(request, response))
                    );
                });
            });
            post("trasfer", (request, response) -> {
                return new Gson().toJson(transactionController.transfer(request, response));
            });
        });

        exception(Exception.class, (e, request, response) -> {
            log.info("Internal server error.");
            response.status(HttpURLConnection.HTTP_INTERNAL_ERROR);
        });
    }
}
