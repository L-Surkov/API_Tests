package testData;

import models.CreateUserRequest;
import models.RegisterUserRequest;
import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {
    static Faker faker = new Faker(new Locale("us"));
    public static final CreateUserRequest validCreateData = new CreateUserRequest();
    public static final RegisterUserRequest validRegisterData = new RegisterUserRequest();
    public static final RegisterUserRequest invalidRegisterData = new RegisterUserRequest();

    public static final String expectedName = faker.name().firstName();
    public static final String expectedJob = faker.job().title();
    public static final String expectedEmail = "eve.holt@reqres.in";
    public static final String expectedPassword = "pistachio";
    public static final String expectedErrorMessage = "Missing password";

    public static final int expectedUserId = 4;
    public static final String expectedUserEmail = "eve.holt@reqres.in";
    public static final String expectedUserFirstName = "Eve";
    public static final String expectedUserLastName = "Holt";
    public static final int invalidUserId = 55;

    static {
        validCreateData.setName(expectedName);
        validCreateData.setJob(expectedJob);

        validRegisterData.setEmail(expectedEmail);
        validRegisterData.setPassword(expectedPassword);

        invalidRegisterData.setEmail(expectedEmail);
    }
}