package ru.yandex.practicum.models.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class MainPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Локаторы
    public By constructorButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Конструктор']");;
    public By headerLoginButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");;
    public By loginButton = By.xpath("//button[text()='Войти в аккаунт']");

    private final By constructorModule = By.className("BurgerIngredients_ingredients__1N8v2");

    @Step("Нажатие кнопки Конструктора")
    public void clickConstructorButton() {
        if (driver.findElement(constructorButton).isDisplayed())  driver.findElement(constructorButton).click();
    }

    @Step("Ожидание отображения Модуля конструктора")
    public boolean constructorModuleIsVisible() {
        return Objects.requireNonNull(wait.until(ExpectedConditions.visibilityOfElementLocated(constructorModule))).isDisplayed();
    }

    @Step("Нажатие кнопки Логин в хедере")
    public void clickHeaderLoginButton() {
        if (driver.findElement(headerLoginButton).isDisplayed())  driver.findElement(headerLoginButton).click();
    }

    @Step("Нажатие кнопки Логин на главной странице")
    public void clickLoginButton() {
        if (driver.findElement(loginButton).isDisplayed())  driver.findElement(loginButton).click();
    }
}