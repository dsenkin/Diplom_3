package ru.yandex.practicum;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.config.RestConfig;
import ru.yandex.practicum.helpers.DriverHelper;

import java.io.IOException;

public class BaseTest {
    WebDriver driver;
    DriverHelper driveHelper;

    @Before
    public void setUp() throws IOException {
        driveHelper = new DriverHelper();
        driver = driveHelper.initDriver();

        //API config
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(RestConfig.HOST)
                .setContentType(ContentType.JSON)
                .build();
        RestAssured.config = RestAssured
                .config()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());

    };

    @After
    public void tearsDown() {driver.quit();};
}