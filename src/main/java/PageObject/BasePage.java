package PageObject;

import utils.RestUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class BasePage {

    protected WebDriver driver;

    @Getter (AccessLevel.PUBLIC)
    protected static RestUtils restUtils;
    protected static JSCommands jsCommands;

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        restUtils = new RestUtils();
        jsCommands = new JSCommands(driver);
        PageFactory.initElements(driver,this);
    }
}
