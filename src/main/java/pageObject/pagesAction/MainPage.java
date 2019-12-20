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
    public MainPage verifyLogo() {
        waitUntilElementIsVisible(imgLogo, TimeOuts.TIME_WAIT_FOR_ELEMENT_LONG, true);
        log.info("Logo is displayed");
        return this;
    }

    @Step("Verification if url is correct")
    public MainPage verifyIfURLisCorrect() {
        Assert.assertEquals(SessionObjects.getBasicUrl(), driver.getCurrentUrl());
        log.info("Url is correct");
        return this;
    }

    @Step("Click Kontakt button")
    public MainPage clickBtnKontakt() {
        waitUntilElementIsClickable(btnKontaktList.get(0), TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, true);
        clickWebElement(btnKontaktList.get(0), TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, true, true);
        log.info("Click button Kontakt");
        return this;
    }

    @Step("Click KONTAKT & ANFAHRT button in footer menu")
    public MainPage clickBtnKontaktAndAnfahrt() {
        jsCommands.jsScrollDown();
        waitUntilElementIsClickable(btnKontaktAndAnfahrtFooterMenu, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, true);
        clickWebElement(btnKontaktAndAnfahrtFooterMenu, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, true, true);
        log.info("Click button KONTAKT & ANFAHRT");
        return this;
    }

    @Step("Verification if Home button is displayed and highlighted")
    public MainPage verifyHomeButton() {
        waitUntilElementIsClickable(btnHome, TimeOuts.TIME_WAIT_FOR_ELEMENT_LONG, true);
        Assert.assertTrue(btnHome.getAttribute("class").contains("current-menu-item"));
        log.info("Logo is displayed");
        return this;
    }

    @Step("Verification HTTP status of main page")
    public MainPage verifyMainPageHTTPStatusAndJSComplete() {
        Assert.assertTrue(restUtils.isPageStatus200(driver));
        jsCommands.jsVerifyPageState();
        return this;
    }

    public MainPage verificationMainPageStuff() {
        return this.verifyIfURLisCorrect()
                .verifyMainPageHTTPStatusAndJSComplete()
                .verifyLogo()
                .verifyHomeButton();
    }


}
