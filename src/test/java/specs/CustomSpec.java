package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import tests.TestBase;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class CustomSpec {
    public static RequestSpecification requestSpec = with()
            .header(TestBase.header)
            .log().uri()
            .log().headers()
            .log().body()
            .contentType(JSON)
            .filter(withCustomTemplates());

    public static ResponseSpecification buildResponseSpec(int expectedStatusCode) {
        ResponseSpecBuilder builder = new ResponseSpecBuilder()
                .expectStatusCode(expectedStatusCode)
                .log(STATUS);

        if (expectedStatusCode == 204) {
            builder.expectBody(equalTo(""));
        } else {
            builder.log(BODY);
        }

        return builder.build();
    }
}