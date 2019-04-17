package integration;

import com.revolut.appsByPravin.MoneyApp.db.H2Database;
import com.revolut.appsByPravin.MoneyApp.route.RouteController;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public class TransactionControllerITest {
    private static final Logger log = LoggerFactory.getLogger(TransactionControllerITest.class);

    @Test
    public void testGetTransactions() {
        RestAssured.given()
                .when()
                .get("http://localhost:8182/moneyapp/v1/transactions")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testTransfer() {
        String body = " { \n" +
                "    \"fromAccountNumber\" = 1,\n" +
                "    \"toAccountNumber\" = 3, \n" +
                "    \"amount\" = 25, \n" +
                "    \"currency\" = 'USD'\n" +
                " }";

        RestAssured.given()
                .contentType(ContentType.TEXT)
                .body(body)
                .when()
                .post("http://localhost:8182/moneyapp/v1/transfer")
                .then()
                .assertThat()
                .statusCode(200);
    }


    @BeforeClass
    public static void setUp() {
        configureAndStartWebServer();
    }

    @AfterClass
    public static void tearDown() {
        stopWebServer();
    }

    private static void configureAndStartWebServer() {

        log.info("Starting Application");
        Spark.port(8182);
        RouteController.initializeRoutes();
        H2Database.initializeDatabase();
        Spark.awaitInitialization();
        log.info("Application has started successfully");
    }

    private static void stopWebServer() {
        Spark.stop();
    }
}
