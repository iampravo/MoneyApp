package integration;

import com.revolut.appsByPravin.MoneyApp.db.H2Database;
import com.revolut.appsByPravin.MoneyApp.route.RouteController;
import io.restassured.RestAssured;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public class UserControllerITest {
    private static final Logger log = LoggerFactory.getLogger(UserControllerITest.class);

    @Test
    public void testGetAllUsers() {
        RestAssured.given()
                .when()
                .get("http://localhost:8182/moneyapp/v1/users")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testGetAllUserById() {
        RestAssured.given()
                .when()
                .get("http://localhost:8182/moneyapp/v1/users/1")
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
