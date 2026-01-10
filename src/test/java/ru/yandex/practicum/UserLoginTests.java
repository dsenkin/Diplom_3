package ru.yandex.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.data.UserData;
import ru.yandex.practicum.models.UserModel;
import ru.yandex.practicum.models.pageobject.ForgotPasswordPage;
import ru.yandex.practicum.models.pageobject.LoginPage;
import ru.yandex.practicum.models.pageobject.MainPage;
import ru.yandex.practicum.models.pageobject.RegisterPage;
import ru.yandex.practicum.steps.ApiUserSteps;

import static ru.yandex.practicum.config.RestConfig.HOST;

public class UserLoginTests extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;

    private ApiUserSteps userSteps;
    private UserModel user;

    @Before
    public void startUp() {
        // Создание пользователя
        user = new UserModel();
        UserData userData = new UserData();
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());
        user.setName(userData.getName());

        userSteps = new ApiUserSteps();
        userSteps.createUser(user);

        // Инициализация моделей страниц
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);


        // Открытие главной страницы
        driver.get(HOST);
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка успешного входа пользователя по кнопке на главной странице, пользователь создается при помощи API")
    public void userLoginFromMainPageLoginButtonTest() {
        mainPage.clickLoginButton();

        loginPage.loginModuleIsVisible();
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLogin();
        Assert.assertTrue(mainPage.constructorModuleIsVisible());
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    @Description("Проверка успешного входа пользователя через кнопку Личный кабинет в шапке, пользователь создается при помощи API")
    public void userLoginFromHeaderButtonTest() {
        mainPage.clickHeaderLoginButton();

        loginPage.loginModuleIsVisible();
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLogin();
        Assert.assertTrue(mainPage.constructorModuleIsVisible());
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    @Description("Проверка успешного входа пользователя по кнопке в форме регистрации, пользователь создается при помощи API")
    public void userLoginFromRegistrationFormTest() {
        mainPage.clickHeaderLoginButton();

        loginPage.loginModuleIsVisible();
        loginPage.clickRegistrationLink();

        registerPage.clickLoginButton();

        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLogin();

        Assert.assertTrue(mainPage.constructorModuleIsVisible());
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")
    @Description("Проверка успешного входа пользователя по кнопке в форме восстановления пароля, пользователь создается при помощи API")
    public void userLoginFromForgotPasswordButtonTest() {
        mainPage.clickHeaderLoginButton();

        loginPage.loginModuleIsVisible();
        loginPage.clickForgotPasswordLink();

        forgotPasswordPage.clickForgotPasswordLink();

        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLogin();
        Assert.assertTrue(mainPage.constructorModuleIsVisible());
    }

    @After
    public void tearDown() {
        String accessToken = userSteps.getToken(user);
        userSteps.deleteUser(accessToken);
    }
}
