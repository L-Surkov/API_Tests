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

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.CustomSpec.buildResponseSpec;

public class PostUserTests extends TestBase {

    private final Faker faker = new Faker(Locale.US);

    @Test
    @Story("API создания и регистрации пользователей")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("allure8")
    @Description("Отправка POST-запроса и создание пользователя")
    @DisplayName("Создание нового пользователя")
    @Tag("ApiTests")
    void createUserTestPositive() {
        CreateUserRequest validCreateData = TestData.generateValidCreateData(faker);

        CreateUserResponse response = step("Отправка запроса на создание нового пользователя", () ->
                given(CustomSpec.requestSpec)
                        .body(validCreateData)
                        .when()
                        .post(UserEndpoints.CREATE_USER.getEndpoint())
                        .then()
                        .spec(buildResponseSpec(201))
                        .extract().as(CreateUserResponse.class)
        );

        step("Проверка: имя, должность, id и дата создания совпадают", () -> {
            assertThat(response.getName()).isEqualTo(validCreateData.getName());
            assertThat(response.getJob()).isEqualTo(validCreateData.getJob());
            assertThat(response.getId()).isNotNull();
            assertThat(response.getCreatedAt()).isNotNull();
        });
    }

    @Test
    @Story("API создания и регистрации пользователей")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("allure8")
    @Description("Отправка POST-запроса и регистрация нового пользователя")
    @DisplayName("Успешная регистрация нового пользователя")
    @Tag("ApiTests")
    void registerNewUserSuccess() {
        RegisterUserRequest validRegisterData = TestData.generateValidRegisterData(faker);

        RegisterUserResponse response = step("Отправка запроса на регистрацию нового пользователя", () ->
                given(CustomSpec.requestSpec)
                        .body(validRegisterData)
                        .when()
                        .post(UserEndpoints.REGISTER_USER.getEndpoint())
                        .then()
                        .spec(buildResponseSpec(200))
                        .extract().as(RegisterUserResponse.class)
        );

        step("Проверка: id и токен присутствуют, токен валидный", () -> {
            assertThat(response.getId()).isNotNull();
            assertThat(response.getToken()).isNotNull();
            assertThat(response.getToken().length()).isGreaterThanOrEqualTo(17);
            assertThat(response.getToken()).matches("\\S[a-zA-Z0-9]*\\S");
        });
    }

    @Test
    @Story("API создания и регистрации пользователей")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("allure8")
    @Description("Отправка POST-запроса и регистрация нового пользователя без пароля")
    @DisplayName("Ошибка при попытке зарегистрировать пользователя без пароля")
    @Tag("ApiTests")
    void registerNewUserNegative() {
        RegisterUserRequest invalidRegisterData = TestData.generateInvalidRegisterData(faker);

        ErrorResponse response = step("Отправка запроса на регистрацию без пароля", () ->
                given(CustomSpec.requestSpec)
                        .body(invalidRegisterData)
                        .when()
                        .post(UserEndpoints.REGISTER_USER.getEndpoint())
                        .then()
                        .spec(buildResponseSpec(400))
                        .extract().as(ErrorResponse.class)
        );

        step("Проверка: в ответе ошибка '" + TestData.getExpectedErrorMessage() + "'", () -> {
            assertThat(response.getError()).isEqualTo(TestData.getExpectedErrorMessage());
        });
    }
}
