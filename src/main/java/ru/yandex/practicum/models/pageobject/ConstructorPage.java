package ru.yandex.practicum.models.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {
    private WebDriver driver;
    private WebElement element;
    private final WebDriverWait wait;

    // Локаторы страницы
    // Локатор кнопки "Конструктор"
    private final By bunTab = By.xpath("//span[text()='Булки']");
    private final By sauceTab = By.xpath("//span[text()='Соусы']");
    private final By fillingTab = By.xpath("//span[text()='Начинки']");
    private final By bunSelection = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]/span[contains(text(), 'Булки')]");
    private final By sauceSelection = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]/span[contains(text(), 'Соусы')]");
    private final By fillingSelection =  By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]/span[contains(text(), 'Начинки')]");
    private final By ingredientsList = By.className("BurgerIngredients_ingredients__menuContainer__Xu3Mo");

    // Конструктор страницы
    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Кликнуть по объекту с данным локатором
    private void clickLocator(By locator) {
        driver.findElement(locator).click();
    }

    @Step("Нажатие на вкладку Булки")
    public void clickBunTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunTab)).click();
    }

    @Step("Нажатие на вкладку Соусы")
    public void clickSauceTab() {
        wait.until(ExpectedConditions.elementToBeClickable(sauceTab)).click();
    }

    @Step("Нажатие на вкладку Начинки")
    public void clickFillingTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingTab)).click();
    }

    @Step("Ожидание отображения области выбора ингредиентов")
    public boolean ingredientsListIsVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(ingredientsList)).isDisplayed();
    }

    @Step("Проверяем, что произошел переход на вкладку Булки")
    public boolean isBunSectionVisible() {
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(bunSelection));
        assert element != null;
        return element.isDisplayed();
    }

    @Step("Проверяем, что произошел переход на вкладку Соусы")
    public boolean isSauceSectionVisible() {
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(sauceSelection));
        return element.isDisplayed();
    }

    @Step("Проверяем, что произошел переход на вкладку Начинки")
    public boolean isFillingSectionVisible() {
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(fillingSelection));
        return element.isDisplayed();
    }
}
