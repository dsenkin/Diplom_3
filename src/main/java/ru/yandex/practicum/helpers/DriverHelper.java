package ru.yandex.practicum.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

public class DriverHelper {
    WebDriver driver;

    public WebDriver initDriver() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\test\\resources\\browser.properties"));
        String browserProperty = properties.getProperty("testBrowser");
        System.out.println("Browser property: " + browserProperty);
        BrowserType browserType = BrowserType.valueOf(browserProperty);
        switch (browserType) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe"); //"src\\test\\resources\\yandexdriver.exe");
                ChromeOptions options = new ChromeOptions();

                options.setBinary("C:\\Users\\dsenk\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");

                driver = new ChromeDriver(options);
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            default: {throw new RemoteException("Unsupported browser type: " + browserProperty);
            }
        }
        return driver;
    }
}
