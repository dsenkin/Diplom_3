package ru.yandex.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.data.UserData;
import ru.yandex.practicum.models.UserModel;
import ru.yandex.practicum.models.pageobject.LoginPage;
import ru.yandex.practicum.models.pageobject.MainPage;
import ru.yandex.practicum.models.pageobject.RegisterPage;
import ru.yandex.practicum.steps.ApiUserSteps;

import static ru.yandex.practicum.config.RestConfig.HOST;

public class UserRegisterTests extends BaseTest {

    private ApiUserSteps userSteps;

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private UserModel user;

    @Before
    public void startUp() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        userSteps = new ApiUserSteps();

        user = new UserModel();
        UserData userData = new UserData();
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());
        user.setName(userData.getName());

        driver.get(HOST);
    }

    @Test
    @DisplayName("Пользователь может зарегистрироваться")
    @Description("Проверка успешной регистрации пользователя, заполнены все поля, проверка по API, присвоен токен")
    public void successUserRegistrationTest() {
        mainPage.clickHeaderLoginButton();
        loginPage.clickRegistrationLink();

        registerPage.fillEmail(user.getEmail());
        registerPage.fillName(user.getName());
        registerPage.fillPassword(user.getPassword());
        registerPage.clickRegister();

        String accessToken = userSteps.getToken(user);
        Assert.assertNotNull(accessToken);
    }

    @Test
    @DisplayName("Ошибка, пароль должен содержать не менее 6 символов")
    @Description("Проверка появления ошибки при невалидном пароле, менее 6 символов")
    public void failedUserLoginWithPasswordLessSixCharTest() {
        String wrongPassword = "qwert"; // 5 символов

        mainPage.clickHeaderLoginButton();
        loginPage.clickRegistrationLink();

        registerPage.fillName(user.getName());
        registerPage.fillEmail(user.getEmail());
        registerPage.fillPassword(wrongPassword);
        registerPage.clickRegister();

        Assert.assertEquals("Некорректный пароль", registerPage.getPasswordErrorText());
    }

    @After
    public void tearDown() {
        String accessToken = userSteps.getToken(user);
        userSteps.deleteUser(accessToken);
    }
}