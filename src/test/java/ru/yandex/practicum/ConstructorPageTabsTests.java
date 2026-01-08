package ru.yandex.practicum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.practicum.models.pageobject.ConstructorPage;
import ru.yandex.practicum.models.pageobject.MainPage;
import io.qameta.allure.junit4.DisplayName;

import java.io.IOException;
import java.util.Objects;

import static ru.yandex.practicum.config.RestConfig.HOST;

public class ConstructorPageTabsTests extends BaseTest {

//    private WebDriver driver;
    private ConstructorPage constructorPage;
    private MainPage mainPage;

    @Before
    public void startUp() throws InterruptedException, IOException {
        constructorPage = new ConstructorPage(driver);
        mainPage = new MainPage(driver);

        driver.get(HOST);

        mainPage.constructorModuleIsVisible();
        Objects.requireNonNull(mainPage.wait.until(ExpectedConditions.elementToBeClickable(mainPage.constructorButton))).click();
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    public void stepIntoBunTabTest() {
        constructorPage.clickSauceTab();
        constructorPage.clickBunTab();
        Assert.assertTrue(constructorPage.isBunSectionVisible());
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void stepIntoSauceTabTest() {
        constructorPage.clickSauceTab();
        Assert.assertTrue(constructorPage.isSauceSectionVisible());
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void stepIntoFillingTabTest() {
        constructorPage.clickFillingTab();
        constructorPage.ingredientsListIsVisible();
        Assert.assertTrue(constructorPage.isFillingSectionVisible());
    }
}