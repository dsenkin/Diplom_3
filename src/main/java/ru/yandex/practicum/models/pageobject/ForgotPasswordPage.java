package ru.yandex.practicum.models.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    private WebDriver driver;
    private WebElement element;
    private final WebDriverWait wait;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Локаторы
    private final By loginLink = By.className("Auth_link__1fOlj");

    @Step("Нажатие на ссылку Войти")
    public void clickForgotPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
}
