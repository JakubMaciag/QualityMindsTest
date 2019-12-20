package pageObject.pagesAction;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObject.locators.PortfolioLocators;
import utils.TimeOuts;

@Slf4j
public class PortfolioPage extends PortfolioLocators {

    public PortfolioPage(WebDriver driver) {
        super(driver);
    }


}
