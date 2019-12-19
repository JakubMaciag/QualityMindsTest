package utils.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

@Slf4j
public class FirefoxUtils {
    private String DOWNLOAD_DIRECTORY = "src/test/resources/tmp";
    private FirefoxOptions prepareFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("handlesAlerts", false);

        firefoxOptions.addPreference("browser.download.dir", DOWNLOAD_DIRECTORY);
         firefoxOptions.addPreference("browser.download.floderList",2);
         firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk","application/pdf");
         firefoxOptions.addPreference("browser.download.useDownloadDir",true);
         firefoxOptions.addPreference("browser.download.manager.showWhenStarting",false);
         firefoxOptions.addPreference("browser.download.manager.useWindow", false);
         firefoxOptions.addPreference("browser.helperApps.alwaysAsk.force",false);
        log.info("Set firefox browser settings");
        return firefoxOptions;
    }

    public WebDriver setUpFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        log.info("Set up firefox browser driver");
        return new FirefoxDriver(prepareFirefoxOptions());
    }
}
