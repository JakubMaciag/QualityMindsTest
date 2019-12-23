package pageObject.pagesAction;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObject.locators.PortfolioLocators;
import utils.AttachUtils;
import utils.TimeOuts;

@Slf4j
public class PortfolioPage extends PortfolioLocators {
    private String linkWebAutomationMobileTesting = "https://qualityminds.de/team_page/wam-testing/";
    private String linkFlyerDownload = "https://qualityminds.de/app/uploads/2018/11/Find-The-Mobile-Bug-Session.pdf";
    private String greenHighlighted = "(130, 186, 69";
    private String greyHighlighted = "(151, 151, 151";
    private String downloadedFileNameFlyer = "FLYER FIND THE BUG SESSION";
    private int offset = 200;

    public PortfolioPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verification if Page Web Automation and Mobile Testing Page url is correct")
    public PortfolioPage verifyIfURLisCorrect() {
        Assert.assertEquals(linkWebAutomationMobileTesting, driver.getCurrentUrl());
        log.info("Url is correct");
        return this;
    }

    @Step("Verification HTTP status of portfolio page")
    public PortfolioPage verifyHTTPStatusAndJSComplete() {
        verifyIfPageIsLoaded();
        return this;
    }

    @Step("Verification if Web Automation and Mobile Testing label exist")
    public PortfolioPage verifyIfLabelWebAutomationAndMobileTestingExists(boolean assertError) {
        waitUntilElementIsVisible(labelWebAutomationMobileTesting, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        log.info("Label exists");
        return this;
    }


    @Step("Go to page Web, Automation & Mobile Testing by sub menu button")
    public PortfolioPage goToWebAutomationAndMobileWebPage(boolean assertError) {
        moveToElementActions(btnWebAutomationAndMobileInSubMenu);
        clickWebElement(btnWebAutomationAndMobileInSubMenu, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, true, assertError);
        log.info("Go to page Web, Automation & Mobile Testing by sub menu button");
        return this;
    }

    @Step("Focus on Portfolio button")
    public PortfolioPage focusOnPortfolioButton(boolean assertError) {
        jsCommands.jsScrollUp();
        waitUntilElementIsClickable(btnPortfolio, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        moveToElementActions(btnPortfolio);
        log.info("Focused on portfolio button");
        return this;
    }

    @Step("Verification if Portfolio button is displayed and highlighted")
    public PortfolioPage verifyPortfolioButton(boolean assertError) {
        waitUntilElementIsClickable(btnPortfolio, TimeOuts.TIME_WAIT_FOR_ELEMENT_LONG, assertError);
        waitUntilCSSValueContains(btnPortfolio, "color", greenHighlighted, TimeOuts.TIME_WAIT_FOR_ELEMENT_LONG, assertError);
        log.info("Portfolio button is highlighted");
        return this;
    }

    @Step("Verification of existence of sub menu under Portfolio")
    public PortfolioPage verifySubMenuUnderPortfolio(boolean assertError) {
        for (WebElement element : subMenuPortfolioList) {
            waitUntilElementIsVisible(element, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
            log.info("Exist: " + element.toString());
        }
        return this;
    }

    @Step("Move and click Mobile button")
    public PortfolioPage clickMobileContentButton(boolean assertError) {
        waitUntilElementIsVisible(btnMobileContent, TimeOuts.TIME_WAIT_FOR_ELEMENT_LONG, assertError);
        jsCommands.jsScrollToPoint(btnMobileContent.getLocation().getX(), btnMobileContent.getLocation().getY() - offset);
        clickWebElement(btnMobileContent, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, true, assertError);
        log.info("Clicked mobile content button");
        return this;
    }

    @Step("Verification if Mobile content button is highlighted")
    public PortfolioPage verifyIfMobileContentButtonIsHighlighted(boolean assertError) {
        waitUntilAttributeContains(underlineOfMobileContentButton, "class", "active-team-tab", TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        waitUntilCSSValueContains(underlineOfMobileContentButton, "border-bottom-color", greyHighlighted, TimeOuts.TIME_WAIT_FOR_ELEMENT_LONG, assertError);
        log.info("Mobile content button is highlighted");
        return this;
    }

    @Step("Verification if Mobile content text exists")
    public PortfolioPage verifyIfMobileContentTextExists(boolean assertError) {
        waitUntilElementIsVisible(textMobileContent, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        log.info("Mobile content text exists");
        return this;
    }

    @Step("Verification if Flyer button is displayed on the right side of mobile content")
    public PortfolioPage verifyIfFlyerButtonIsDisplayedOnRight(boolean assertError) {
        waitUntilElementIsClickable(btnFlyer, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        int locationParameter = btnFlyer.getLocation().getX() - textMobileContent.getLocation().getX();
        Assert.assertTrue(locationParameter > 0);
        log.info("Flyer button is displayed on the right side of content");
        return this;
    }

    @Step("Verification if Flyer button contains specified link")
    public PortfolioPage verifyIfFlyerButtonContainsLinkToFile(boolean assertError) {
        Allure.attachment("Link", linkFlyerDownload);
        waitUntilAttributeContains(btnFlyer, "href", linkFlyerDownload, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        log.info("Flyer button contains link: " + linkFlyerDownload);
        return this;
    }

    @Step("Click Flyer button")
    public PortfolioPage clickFlyerButton(boolean assertError) {
        clickWebElement(btnFlyer, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, true, assertError);
        log.info("Clicked Flyer button");
        return this;
    }

    @Step("Verification if file can be downloaded")
    public PortfolioPage verifyIfFileCanBeDownloaded(String path, String brwoser, boolean assertError) {
        if (brwoser.equals("chrome"))
            path = path + "\\" + downloadedFileNameFlyer + ".pdf";
        else if (brwoser.equals("firefox"))
            path = path + "\\" + downloadedFileNameFlyer;
        else
            path = null;
        new AttachUtils(driver).verifyIfFileExist(path, assertError);
        log.info("File exist:" + path);
        Allure.attachment("File exist ", path);
        return this;
    }

    public PortfolioPage clearDownloadDirectory(String path) {
        new AttachUtils(driver).clearDirectory(path);
        return this;
    }

    public PortfolioPage verifyExistenceOfSubMenuUnderPortfolioButton(boolean assertError) {
        return this.focusOnPortfolioButton(assertError)
                .verifySubMenuUnderPortfolio(assertError);
    }

    public PortfolioPage goToWebAutomationAndMobileWebPageAndVerifyPage(boolean assertError) {
        return this.goToWebAutomationAndMobileWebPage(assertError)
                .verifyIfURLisCorrect()
                .verifyHTTPStatusAndJSComplete();
    }

    public PortfolioPage verifyMobileContentAndFlyerButton(boolean assertError) {
        return this.verifyIfLabelWebAutomationAndMobileTestingExists(assertError)
                .clickMobileContentButton(assertError)
                .verifyIfMobileContentButtonIsHighlighted(assertError)
                .verifyIfMobileContentTextExists(assertError)
                .verifyIfFlyerButtonIsDisplayedOnRight(assertError);
    }

    public PortfolioPage verifyIfFileIsAvailableViaLink(String path, String browser, boolean assertError) {
        return this.clearDownloadDirectory(path)
                .clickFlyerButton(assertError)
                .verifyIfFileCanBeDownloaded(path, browser, assertError)
                .clearDownloadDirectory(path);
    }
}
