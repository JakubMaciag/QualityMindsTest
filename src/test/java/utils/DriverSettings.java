package utils;

import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import utils.browsers.ChromeUtils;
import utils.browsers.FirefoxUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
class DriverSettings {
    public DriverSettings() {
        firefoxUtils = new FirefoxUtils();
        chromeUtils = new ChromeUtils();
    }

    @NonNull
    private static WebDriver driver;
    @NonNull
    private static FirefoxUtils firefoxUtils;
    @NonNull
    private static ChromeUtils chromeUtils;

    @Step("Run browser")
    static WebDriver runBrowser() {
        if (SessionObjects.getBrowserName().equals("chrome"))
            driver = chromeUtils.setUpChromeDriver();
        else if (SessionObjects.getBrowserName().equals("firefox"))
            driver = firefoxUtils.setUpFirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(TimeOuts.TIME_DRIVER_IMPLICITY_WAIT, TimeUnit.SECONDS);
        driver.get(SessionObjects.getBasicUrl());
        log.info("Run webdriver on the page: " + SessionObjects.getBasicUrl());
        return driver;
    }

    @Step("Close browser")
    static void closeBrowser(WebDriver driver) {
        driver.quit();
        log.info("Browser is closed.");
    }
}
