package tests;

import endpoints.UserEndpoints;
import io.qameta.allure.*;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import specs.CustomSpec;
import testData.TestData;
import com.github.javafaker.Faker;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static specs.CustomSpec.buildResponseSpec;

public class PostUserTests extends TestBase {

    @Test
    @Story("API создания и регистрации пользователей")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("allure8")
    @Description("Отправка POST-запроса и создание пользователя")
    @DisplayName("Проверка успешного создания нового пользователя")
    @Tag("ApiTests")
    @Step("Отправка запроса на создание нового пользователя")
    void createUserTestPositive() {
        Faker faker = new Faker(Locale.US);
        CreateUserRequest validCreateData = TestData.generateValidCreateData(faker);

        CreateUserResponse response = given(CustomSpec.requestSpec)
                .body(validCreateData)
                .when()
                .post(UserEndpoints.CREATE_USER.getEndpoint())
                .then()
                .spec(buildResponseSpec(201))
                .extract().as(CreateUserResponse.class);

        checkCreateUserResponse(response, validCreateData);
    }

    @Step("Проверка успешного создания пользователя")
    private void checkCreateUserResponse(CreateUserResponse response, CreateUserRequest requestData) {
        assertThat(response.getName()).isEqualTo(requestData.getName());
        assertThat(response.getJob()).isEqualTo(requestData.getJob());
        assertThat(response.getId()).isNotNull();
        assertThat(response.getCreatedAt()).isNotNull();
    }

    @Test
    @Story("API создания и регистрации пользователей")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("allure8")
    @Description("Отправка POST-запроса и регистрация нового пользователя")
    @DisplayName("Проверка успешной регистрации нового пользователя")
    @Tag("ApiTests")
    @Step("Отправка запроса на регистрацию нового пользователя")
    void registerNewUserSuccess() {
        Faker faker = new Faker(Locale.US);
        RegisterUserRequest validRegisterData = TestData.generateValidRegisterData(faker);

        RegisterUserResponse response = given(CustomSpec.requestSpec)
                .body(validRegisterData)
                .when()
                .post(UserEndpoints.REGISTER_USER.getEndpoint())
                .then()
                .spec(buildResponseSpec(200))
                .extract().as(RegisterUserResponse.class);

        checkRegisterUserResponse(response);
    }

    @Step("Проверка успешной регистрации пользователя")
    private void checkRegisterUserResponse(RegisterUserResponse response) {
        assertThat(response.getId()).isNotNull();
        assertThat(response.getToken()).isNotNull();
        assertThat(response.getToken().length()).isGreaterThanOrEqualTo(17);
        assertThat(response.getToken()).matches("\\S[a-zA-Z0-9]*\\S");
    }

    @Test
    @Story("API создания и регистрации пользователей")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("allure8")
    @Description("Отправка POST-запроса и регистрация нового пользователя")
    @DisplayName("Проверка ошибки при попытке зарегистрировать пользователя без пароля")
    @Tag("ApiTests")
    @Step("Отправка запроса на регистрацию нового пользователя без пароля")
    void registerNewUserNegative() {
        Faker faker = new Faker(Locale.US);
        RegisterUserRequest invalidRegisterData = TestData.generateInvalidRegisterData(faker);

        ErrorResponse response = given(CustomSpec.requestSpec)
                .body(invalidRegisterData)
                .when()
                .post(UserEndpoints.REGISTER_USER.getEndpoint())
                .then()
                .spec(buildResponseSpec(400))
                .extract().as(ErrorResponse.class);

        checkErrorResponse(response);
    }

    @Step("Проверка ошибки в ответе при регистрации пользователя без пароля")
    private void checkErrorResponse(ErrorResponse response) {
        assertThat(response.getError()).isEqualTo(TestData.getExpectedErrorMessage());
    }
}