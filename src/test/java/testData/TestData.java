package testData;

import models.CreateUserRequest;
import models.RegisterUserRequest;
import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {

    public static CreateUserRequest generateValidCreateData(Faker faker) {
        CreateUserRequest data = new CreateUserRequest();
        data.setName(faker.name().firstName());
        data.setJob(faker.job().title());
        return data;
    }


    public static RegisterUserRequest generateValidRegisterData(Faker faker) {
        RegisterUserRequest data = new RegisterUserRequest();
        data.setEmail("eve.holt@reqres.in"); // Фиксированный email
        data.setPassword("pistachio");       // Фиксированный пароль
        return data;
    }


    public static RegisterUserRequest generateInvalidRegisterData(Faker faker) {
        RegisterUserRequest data = new RegisterUserRequest();
        data.setEmail("eve.holt@reqres.in"); // Без пароля
        return data;
    }


    public static String getExpectedErrorMessage() {
        return "Missing password";
    }


    public static int getExpectedUserId() {
        return 4;
    }


    public static String getExpectedUserEmail() {
        return "eve.holt@reqres.in";
    }


    public static String getExpectedUserFirstName() {
        return "Eve";
    }


    public static String getExpectedUserLastName() {
        return "Holt";
    }


    public static int getInvalidUserId() {
        return 55;
    }
}