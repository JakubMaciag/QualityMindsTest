package utils;

import PageObject.BasePage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class BaseTest {
    public BaseTest() {
        super();
    }

    @Getter(AccessLevel.PUBLIC)
    protected static WebDriver driver;

}
