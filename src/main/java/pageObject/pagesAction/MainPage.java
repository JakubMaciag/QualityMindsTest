package pageObject.pagesAction;

import pageObject.locators.MainLocators;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.SessionObjects;
import utils.TimeOuts;
@Slf4j
public class MainPage extends MainLocators {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verification if logo is displayed")
    public MainPage verifyLogo(){
        waitUntilElementIsVisible(imgLogo, TimeOuts.TIME_WAIT_FOR_ELEMENT_LONG,true);
        Assert.assertTrue(isDisplayed(imgLogo));
        log.info("Logo is displayed");
        return this;
    }

    @Step("Verification if url is correct")
    public MainPage verifyIfURLisCorrect(){
        Assert.assertEquals(SessionObjects.getBasicUrl(), driver.getCurrentUrl());
        log.info("Url is correct");
        return this;
    }

    @Step("Click Kontakt button")
    public MainPage clickBtnKontakt(){
        waitUntilElementIsClickable(btnKontaktList.get(0),TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL,true);
        clickWebElement(btnKontaktList.get(0),TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL,true,true);
        log.info("Click button Kontakt");
        return this;
    }


}
