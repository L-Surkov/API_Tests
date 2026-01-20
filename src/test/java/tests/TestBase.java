package tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    public static final String API_KEY = System.getenv("API_KEY");
    public static final Header header = new Header("x-api-key", API_KEY);
    @BeforeAll
    public static void setupEnvironment() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";

    }
}