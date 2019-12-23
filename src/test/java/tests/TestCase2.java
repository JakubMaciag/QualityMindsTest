package tests;

import org.testng.annotations.Test;
import pageObject.pagesAction.MainPage;
import pageObject.pagesAction.PortfolioPage;
import utils.AttachUtils;
import utils.BaseTest;

public class TestCase2 extends BaseTest {
    private boolean assertError = true;

    @Test(description = "Open QM main page")
    public void openMainPage() {
        driver.get(propertiesUtils.getUrl());
        new MainPage(driver).verificationMainPageStuff(propertiesUtils.getUrl(), assertError);
        new AttachUtils(driver).takeScreenShot("Step_1");
    }

    @Test(description = "Hover on Portfolio and verify sub menu", dependsOnMethods = {"openMainPage"})
    public void hoverOnPortfolioAndVerifySubMenu() {
        new PortfolioPage(driver).verifyExistenceOfSubMenuUnderPortfolioButton(assertError);
        new AttachUtils(driver).takeScreenShot("Step_2");
    }

    @Test(description = "Go to page Web, Automotion & Mobile Testing", dependsOnMethods = {"hoverOnPortfolioAndVerifySubMenu"})
    public void goToPageWebAutomationAndMobileTesting() {
        new PortfolioPage(driver).goToWebAutomationAndMobileWebPageAndVerifyPage(assertError);
        new AttachUtils(driver).takeScreenShot("Step_3");
    }

    @Test(description = "Verification if Portfolio button is highlighted", dependsOnMethods = {"goToPageWebAutomationAndMobileTesting"})
    public void verifyPortfolioButton() {
        new PortfolioPage(driver).verifyPortfolioButton(true);
        new AttachUtils(driver).takeScreenShot("Step_4");
    }

    @Test(description = "Verification if Mobile section and Flyer button are displayed", dependsOnMethods = {"verifyPortfolioButton"})
    public void verifyMobileSectionAndFlyreButton() {
        new PortfolioPage(driver).verifyMobileContentAndFlyerButton(assertError);
        new AttachUtils(driver).takeScreenShot("Step_5");
    }

    @Test(description = "Verification if Flyer button contains specified link to file", dependsOnMethods = {"verifyMobileSectionAndFlyreButton"})
    public void verifyFlyerButtonContainsLink() {
        new PortfolioPage(driver).verifyIfFlyerButtonContainsLinkToFile(true);
        new AttachUtils(driver).takeScreenShot("Step_6");
    }

    @Test(description = "Verification if file is available via download link", dependsOnMethods = {"verifyFlyerButtonContainsLink"})
    public void verifyIfFileIsAvailableViaDownloadedLink() {
        new PortfolioPage(driver).verifyIfFileIsAvailableViaLink(getDownloadDirectory(), getBrowserName(), assertError);
        new AttachUtils(driver).takeScreenShot("Step_7");
    }
}
