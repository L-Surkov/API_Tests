package tests;

import endpoints.UserEndpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import models.ErrorResponse;
import models.User;
import models.UserListResponse;
import models.UserSingleResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import specs.CustomSpec;
import testData.TestData;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.CustomSpec.buildResponseSpec;

public class GetUserTests extends TestBase {

    @Test
    @Description("Отправка GET-запроса и вывод списка пользователей")
    @DisplayName("Проверка получения списка пользователей по номеру страницы")
    @Tag("ApiTests")
    @Step("Запрос списка пользователей по странице")
    void getListUsersTestPositive() {
        UserListResponse response = given(CustomSpec.requestSpec)
                .queryParam("page", 1)
                .when()
                .get(UserEndpoints.LIST_USERS.getEndpoint())
                .then()
                .spec(buildResponseSpec(200))
                .extract().as(UserListResponse.class);

        checkListUsers(response);
    }

    @Step("Проверка отображения списка пользователей и всех параметров")
    private void checkListUsers(UserListResponse response) {
        assertThat(response.getPage()).isEqualTo(1);
        assertThat(response.getData()).hasSize(6);
    }

    @Test
    @Description("Отправка GET-запроса и вывод конкретного пользователя")
    @DisplayName("Получение конкретного пользователя по id")
    @Tag("ApiTests")
    @Step("Запрос пользователя по id")
    void getUserByIdTest() {
        UserSingleResponse response = given(CustomSpec.requestSpec)
                .pathParam("id", TestData.getExpectedUserId())
                .when()
                .get(UserEndpoints.SINGLE_USER.getEndpoint())
                .then()
                .spec(buildResponseSpec(200))
                .extract().as(UserSingleResponse.class);

        checkUserById(response);
    }

    @Step("Проверка отображения конкретного пользователя и всех параметров")
    private void checkUserById(UserSingleResponse response) {
        User user = response.getData();
        assertThat(user.getId()).isEqualTo(TestData.getExpectedUserId());
        assertThat(user.getEmail()).isEqualTo(TestData.getExpectedUserEmail());
        assertThat(user.getFirstName()).isEqualTo(TestData.getExpectedUserFirstName());
        assertThat(user.getLastName()).isEqualTo(TestData.getExpectedUserLastName());
    }

    @Test
    @Description("Отправка GET-запроса с id несуществующего пользователя")
    @DisplayName("Проверка корректной ошибки в ответе, если пользователь не найден")
    @Tag("ApiTests")
    @Step("Запрос пользователя по несуществующему id")
    void getNotFoundUserTest() {
        ErrorResponse response = given(CustomSpec.requestSpec)
                .pathParam("id", TestData.getInvalidUserId())
                .log().uri()
                .when()
                .get(UserEndpoints.SINGLE_USER.getEndpoint())
                .then()
                .spec(buildResponseSpec(404))
                .extract().as(ErrorResponse.class);

        checkErrorResponse(response);
    }

    @Step("Проверка отображения ошибки 404")
    private void checkErrorResponse(ErrorResponse response) {
        assertThat(response.getError()).isEqualTo(null); // Тут возможно ошибка в проверке (null?)
    }
}