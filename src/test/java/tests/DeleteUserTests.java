package tests;

import endpoints.UserEndpoints;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import specs.CustomSpec;
import testData.TestData;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.CustomSpec.buildResponseSpec;

public class DeleteUserTests extends TestBase {

    @Test
    @Story("API удаления пользователей")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("allure8")
    @DisplayName("Удаление пользователя")
    @Tag("ApiTests")
    void deleteUserTestPositive() {
        int userId = TestData.getExpectedUserId();

        step("Отправить DELETE-запрос на удаление пользователя с ID: " + userId, () ->
                given(CustomSpec.requestSpec)
                        .pathParam("id", userId)
                        .when()
                        .delete(UserEndpoints.DELETE_USER.getEndpoint())
                        .then()
                        .spec(buildResponseSpec(204))
        );
    }
}
