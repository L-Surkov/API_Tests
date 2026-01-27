package tests;

import endpoints.UserEndpoints;
import io.qameta.allure.*;
import models.ErrorResponse;
import models.User;
import models.UserListResponse;
import models.UserSingleResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import specs.CustomSpec;
import testData.TestData;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.CustomSpec.buildResponseSpec;

public class GetUserTests extends TestBase {

    @Test
    @Story("API получения пользователей")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("allure8")
    @Description("Отправка GET-запроса и вывод списка пользователей")
    @DisplayName("Получение списка пользователей по номеру страницы")
    @Tag("ApiTests")
    void getListUsersTestPositive() {
        UserListResponse response = step("Запрос списка пользователей по странице", () -> given(CustomSpec.requestSpec)
                .queryParam("page", 1)
                .when()
                .get(UserEndpoints.LIST_USERS.getEndpoint())
                .then()
                .spec(buildResponseSpec(200))
                .extract().as(UserListResponse.class));

        step("Проверка: страница = 1, количество пользователей = 6", () -> {
            assertThat(response.getPage()).isEqualTo(1);
            assertThat(response.getData()).hasSize(6);
        });
    }

    @Test
    @Story("API получения пользователей")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("allure8")
    @Description("Отправка GET-запроса и вывод конкретного пользователя")
    @DisplayName("Получение конкретного пользователя по id")
    @Tag("ApiTests")
    void getUserByIdTest() {
        UserSingleResponse response = step("Запрос пользователя по id", () -> given(CustomSpec.requestSpec)
                .pathParam("id", TestData.getExpectedUserId())
                .when()
                .get(UserEndpoints.SINGLE_USER.getEndpoint())
                .then()
                .spec(buildResponseSpec(200))
                .extract().as(UserSingleResponse.class));

        step("Проверка данных пользователя", () -> {
            User user = response.getData();
            assertThat(user.getId()).isEqualTo(TestData.getExpectedUserId());
            assertThat(user.getEmail()).isEqualTo(TestData.getExpectedUserEmail());
            assertThat(user.getFirstName()).isEqualTo(TestData.getExpectedUserFirstName());
            assertThat(user.getLastName()).isEqualTo(TestData.getExpectedUserLastName());
        });
    }

    @Test
    @Story("API получения пользователей")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("allure8")
    @Description("Отправка GET-запроса с id несуществующего пользователя")
    @DisplayName("Ошибка в ответе, если пользователь не найден")
    @Tag("ApiTests")
    void getNotFoundUserTest() {
        ErrorResponse response = step("Запрос пользователя по несуществующему id", () -> given(CustomSpec.requestSpec)
                .pathParam("id", TestData.getInvalidUserId())
                .log().uri()
                .when()
                .get(UserEndpoints.SINGLE_USER.getEndpoint())
                .then()
                .spec(buildResponseSpec(404))
                .extract().as(ErrorResponse.class));

        step("Проверка: ответ пустой (ошибка 404)", () -> {
            assertThat(response.getError()).isNull();
        });
    }
}