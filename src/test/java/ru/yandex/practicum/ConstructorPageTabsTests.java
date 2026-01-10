package ru.yandex.practicum;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.models.pageobject.ConstructorPage;
import ru.yandex.practicum.models.pageobject.MainPage;
import io.qameta.allure.junit4.DisplayName;

import java.io.IOException;

import static ru.yandex.practicum.config.RestConfig.HOST;

public class ConstructorPageTabsTests extends BaseTest {
    private ConstructorPage constructorPage;
    private MainPage mainPage;

    @Before
    public void startUp() throws InterruptedException, IOException {
        constructorPage = new ConstructorPage(driver);
        mainPage = new MainPage(driver);

        driver.get(HOST);

        mainPage.constructorModuleIsVisible();
        mainPage.clickConstructorButton();
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    @Description("Проверка успешного перехода на вкладку Булки")
    public void stepIntoBunTabTest() {
        constructorPage.clickSauceTab();
        constructorPage.clickBunTab();
        Assert.assertTrue(constructorPage.isBunSectionVisible());
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    @Description("Проверка успешного перехода на вкладку Соусы")
    public void stepIntoSauceTabTest() {
        constructorPage.clickSauceTab();
        Assert.assertTrue(constructorPage.isSauceSectionVisible());
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    @Description("Проверка успешного перехода на вкладку Начинки")
    public void stepIntoFillingTabTest() {
        constructorPage.clickFillingTab();
        constructorPage.ingredientsListIsVisible();
        Assert.assertTrue(constructorPage.isFillingSectionVisible());
    }
}