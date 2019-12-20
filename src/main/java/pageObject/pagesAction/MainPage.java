package pageObject.pagesAction;

import jdk.javadoc.internal.doclets.toolkit.util.JavaScriptScanner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pageObject.locators.MainLocators;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.TimeOuts;

@Slf4j
public class MainPage extends MainLocators {
private String linkWebAutomationMobileTesting = "https://qualityminds.de/team_page/wam-testing/";
private String greenHighlighted="rgba(130, 186, 69, 1)";
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
    public MainPage verifyIfURLisCorrect(String basicUrl) {
        Assert.assertEquals(basicUrl, driver.getCurrentUrl());
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
//        Assert.assertTrue(btnHome.getCssValue("color").equals(greenHighlighted));
        Assert.assertTrue(btnHome.getAttribute("class").contains("current-menu-item"));
        log.info("Home button is highlighted");
        return this;
    }

    @Step("Verification HTTP status of main page")
    public MainPage verifyHTTPStatusAndJSComplete() {
        Assert.assertTrue(restUtils.isPageStatus200(driver));
        jsCommands.jsVerifyPageState();
        return this;
    }

    @Step("Focus on Portfolio button")
    public MainPage focusOnPortfolioButton(){
        jsCommands.jsScrollUp();
        waitUntilElementIsClickable(btnPortfolio,TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL,true);
        moveToElementActions(btnPortfolio);
        return this;
    }

    @Step("Verify existance of sub menu under Portfolio")
    public MainPage verifySubMenuUnderPortfolio(){
        for (WebElement element : subMenuPortfolioList) {
            waitUntilElementIsVisible(element, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, true);
        }
        return this;
    }

    @Step("Go to page Web, Automation & Mobile Testing by sub menu button")
        public MainPage goToWebAutomationAndMobileWebPage(){
        moveToElementActions(btnWebAutomationAndMobileInSubMenu);
        clickWebElement(btnWebAutomationAndMobileInSubMenu,TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL,true,true);
        log.info("Go to page Web, Automation & Mobile Testing by sub menu button");
        return this;
    }

    @Step("Verification if Portfolio button is displayed and highlighted")
    public MainPage verifyPortfolioButton() {
        waitUntilElementIsClickable(btnPortfolio, TimeOuts.TIME_WAIT_FOR_ELEMENT_LONG, true);
        Assert.assertTrue(btnPortfolio.getCssValue("color").equals(greenHighlighted));
        log.info("Portfolio button is highlighted");
        return this;
    }


    public MainPage verificationMainPageStuff(String basicUrl) {
        return this.verifyIfURLisCorrect(basicUrl)
                .verifyHTTPStatusAndJSComplete()
                .verifyLogo()
                .verifyHomeButton();
    }

    public MainPage verifyExistanceOfSubMenuUnderPortfolioButton(){
        return this.focusOnPortfolioButton()
                .verifySubMenuUnderPortfolio();
    }

    public MainPage goToWebAutomationAndMobileWebPageAndVerifyPage(){
        return this.goToWebAutomationAndMobileWebPage()
                .verifyIfURLisCorrect(linkWebAutomationMobileTesting)
                .verifyHTTPStatusAndJSComplete();
    }
}
