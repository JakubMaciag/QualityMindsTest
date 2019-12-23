package utils.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ChromeUtils {

    private ChromeOptions prepareChromeOptions(String downloadDirectory) {
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + downloadDirectory);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");
        chromePrefs.put("useAutomationExtension", false);
        chromeOptions.addArguments("--test-type");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-software-rasterizer");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        log.info("Set chrome browser settings");
        return chromeOptions;
    }

    public WebDriver setUpChromeDriver(String downloadDirectory) {
        WebDriverManager.chromedriver().setup();
        log.info("Set up chrome browser driver");
        return new ChromeDriver(prepareChromeOptions(downloadDirectory));
    }
}

