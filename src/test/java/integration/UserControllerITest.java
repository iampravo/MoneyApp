package integration;

import com.revolut.appsByPravin.MoneyApp.db.H2Database;
import com.revolut.appsByPravin.MoneyApp.route.RouteController;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public class UserControllerITest {
    private static final Logger log = LoggerFactory.getLogger(UserControllerITest.class);

    @Test
    public void testUserControllerEndPoints() {
        RestAssured.given()
                .when()
                .get("http://localhost:8182/moneyapp/v1/users")
                .then()
                .assertThat()
                .statusCode(200);
        RestAssured.given()
                .when()
                .get("http://localhost:8182/moneyapp/v1/users/1")
                .then()
                .assertThat()
                .statusCode(200);
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