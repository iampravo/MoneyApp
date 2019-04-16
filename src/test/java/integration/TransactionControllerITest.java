package integration;

import com.google.gson.Gson;
import com.revolut.appsByPravin.MoneyApp.db.H2Database;
import com.revolut.appsByPravin.MoneyApp.route.RouteController;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import java.util.HashMap;
import java.util.Map;

public class TransactionControllerITest {
    private static final Logger log = LoggerFactory.getLogger(TransactionControllerITest.class);

    @Test
    public void testTransactionControllerEndpoint() {
        RestAssured.given()
                .when()
                .get("http://localhost:8182/moneyapp/v1/transactions")
                .then()
                .assertThat()
                .statusCode(200);

        Map<String, String> transaction = new HashMap<>();
        transaction.put("fromAccountNumber", "1");
        transaction.put("toAccountNumber", "3");
        transaction.put("amount", "25");
        transaction.put("currency", "USD");

        /*RestAssured.given()
                .contentType("application/json")
                .body(new Gson().toJson(transaction))
                .when()
                .get("http://localhost:8182/moneyapp/v1/transfer")
                .then()
                .assertThat()
                .statusCode(200);*/
    }


    @Before
    public void setUp() {
        configureAndStartWebServer();
    }

    @After
    public void tearDown() {
        stopWebServer();
    }

    private void configureAndStartWebServer() {

        log.info("Starting Application");
        Spark.port(8182);
        RouteController.initializeRoutes();
        H2Database.initializeDatabase();
        Spark.awaitInitialization();
        log.info("Application has started successfully");
    }

    private void stopWebServer() {
        Spark.stop();
    }

}