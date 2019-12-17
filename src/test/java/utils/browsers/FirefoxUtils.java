package utils.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

@Slf4j
public class FirefoxUtils {

@Step("Set firefox browser settings")
    private FirefoxOptions prepareFirefoxOptions(){
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.setCapability("handlesAlerts",false);
    log.info("Set firefox browser settings");
    return firefoxOptions;
}

@Step("Set up firefox driver")
    public WebDriver setUpFirefoxDriver(){
    WebDriverManager.firefoxdriver().setup();
    log.info("Set up firefox browser driver");
    return new FirefoxDriver(prepareFirefoxOptions());
}
}
