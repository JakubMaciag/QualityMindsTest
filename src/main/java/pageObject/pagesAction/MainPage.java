package pageObject.pagesAction;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObject.locators.MainLocators;
import utils.TimeOuts;

@Slf4j
public class MainPage extends MainLocators {

    private String greenHighlighted = "130, 186, 69";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verification if logo is displayed")
    public MainPage verifyLogo(boolean assertError) {
        waitUntilElementIsVisible(imgLogo, TimeOuts.TIME_WAIT_FOR_ELEMENT_LONG, assertError);
        log.info("Logo is displayed");
        return this;
    }

    @Step("Verification if Cookies message exists")
    public MainPage verifyBtnAcceptCookies(boolean assertError) {
        waitUntilElementIsVisible(btnAcceptCookies, TimeOuts.TIME_WAIT_FOR_ELEMENT_LONG, assertError);
        log.info("Accept Cookies button is displayed");
        return this;
    }

    @Step("Verification if url is correct")
    public MainPage verifyIfURLisCorrect(String basicUrl) {
        Assert.assertEquals(basicUrl, driver.getCurrentUrl());
        log.info("Url is correct");
        return this;
    }

    @Step("Click KONTAKT & ANFAHRT button in footer menu")
    public MainPage clickBtnKontaktAndAnfahrt(boolean assertError) {
        jsCommands.jsScrollDown();
        waitUntilElementIsClickable(btnKontaktAndAnfahrtFooterMenu, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        clickWebElement(btnKontaktAndAnfahrtFooterMenu, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, true, assertError);
        log.info("Clicked button KONTAKT & ANFAHRT");
        return this;
    }

    @Step("Verification if Home button is displayed and highlighted")
    public MainPage verifyHomeButton(boolean assertError) {
        waitUntilElementIsClickable(btnHome, TimeOuts.TIME_WAIT_FOR_ELEMENT_LONG, assertError);
        waitUntilCSSValueContains(btnHome, "color", greenHighlighted, TimeOuts.TIME_WAIT_FOR_ELEMENT_LONG, assertError);
        waitUntilAttributeContains(btnHomeBackground, "class", "current-menu-item",
                TimeOuts.TIME_WAIT_FOR_ELEMENT_LONG, assertError);
        log.info("Home button is highlighted");
        return this;
    }

    @Step("Verification HTTP status of main page")
    public MainPage verifyHTTPStatusAndJSComplete() {
        verifyIfPageIsLoaded();
        return this;
    }

    public MainPage verificationMainPageStuff(String basicUrl, boolean assertError) {
        return this.verifyIfURLisCorrect(basicUrl)
                .verifyHTTPStatusAndJSComplete()
                .verifyLogo(assertError)
                .verifyBtnAcceptCookies(assertError)
                .verifyHomeButton(assertError);
    }
}
