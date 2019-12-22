package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import utils.browsers.ChromeUtils;
import utils.browsers.FirefoxUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
class DriverSettings {
    private PropertiesUtils propertiesUtils;
    private WebDriver driver;
    private FirefoxUtils firefoxUtils;
    private ChromeUtils chromeUtils;
    public DriverSettings(PropertiesUtils propertiesUtils) {
        this.propertiesUtils = propertiesUtils;
        firefoxUtils = new FirefoxUtils();
        chromeUtils = new ChromeUtils();
    }

    @Step("Run browser")
    public WebDriver runBrowser(String browserName) {
        Allure.attachment("Browser: ", browserName);
        try {
            if (browserName.equals("chrome"))
                driver = chromeUtils.setUpChromeDriver(propertiesUtils.getDownloadDirecotryForChrome());
            else if (browserName.trim().equals("firefox"))
                driver = firefoxUtils.setUpFirefoxDriver(propertiesUtils.getDownloadDirecotryForFirefox());
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(TimeOuts.TIME_DRIVER_IMPLICITY_WAIT, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(TimeOuts.TIME_DRIVER_PAGELOAD_WAIT, TimeUnit.SECONDS);
            log.info("Run webdriver");
        } catch (Exception e) {
            log.error("Error with set up driver");
        }
        return driver;
    }

    @Step("Close browser")
    void closeBrowser(WebDriver driver) {
        driver.quit();
        log.info("Browser is closed.");
    }
}
