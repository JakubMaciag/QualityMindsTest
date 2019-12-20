package utils.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;


@Slf4j
public class FirefoxUtils {
    private String DOWNLOAD_DIRECTORY = System.getProperty("user.dir") + "\\src\\test\\resources\\tmpFirefox";

    private DesiredCapabilities prepareFirefoxOptions() {
        FirefoxProfile profile = new FirefoxProfile();
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("handlesAlerts", false);
        profile.setPreference("browser.download.dir", DOWNLOAD_DIRECTORY);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/x-pdf,application/octet-stream");
        profile.setPreference("browser.download.useDownloadDir", true);
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.manager.useWindow", false);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        capabilities.setCapability(FirefoxDriver.PROFILE,profile);
        log.info("Set firefox browser settings");
        return capabilities;
    }

    public WebDriver setUpFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        log.info("Set up firefox browser driver");
        // I truly know that is deprecated but FirefoxOptions doesn't work on my stuff.
        return new FirefoxDriver(prepareFirefoxOptions());
    }
}
