package utils.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Slf4j
public class ChromeUtils {

    private ChromeOptions prepareChromeOptions(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("handlesAlerts",false);
        log.info("Set chrome browser settings");
        return chromeOptions;
    }

    public WebDriver setUpChromeDriver(){
        WebDriverManager.chromedriver().setup();
        log.info("Set up chrome browser driver");
        return new ChromeDriver(prepareChromeOptions());
    }
}

