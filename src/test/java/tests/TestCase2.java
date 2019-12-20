package tests;

import org.testng.annotations.Test;
import pageObject.pagesAction.MainPage;
import utils.AttachUtils;
import utils.BaseTest;

public class TestCase2 extends BaseTest {

    @Test(description = "Open QM main page")
    public void openMainPage() {
        driver.get(propertiesUtils.getUrl());
        new MainPage(driver).verificationMainPageStuff(propertiesUtils.getUrl());
        new AttachUtils(driver).takeScreenShot("Step_1");
    }

    @Test(description = "Hover on Portfolio and verify sub menu",dependsOnMethods = {"openMainPage"})
    public void hoverOnPortfolioAndVerifySubMenu() {
        new MainPage(driver).verifyExistanceOfSubMenuUnderPortfolioButton();
        new AttachUtils(driver).takeScreenShot("Step_2");
    }

    @Test(description = "Go to page Web, Automotion & Mobile Testing", dependsOnMethods = {"hoverOnPortfolioAndVerifySubMenu"})
    public void goToPageWebAutomationAndMobileTesting() {
        new MainPage(driver).goToWebAutomationAndMobileWebPageAndVerifyPage();
        new AttachUtils(driver).takeScreenShot("Step_3");

    }
    @Test(description = "Verification if Portfolio button is highlighted", dependsOnMethods = {"goToPageWebAutomationAndMobileTesting"})
    public void verifyPortfolioButton() {
        new MainPage(driver).verifyPortfolioButton();
        new AttachUtils(driver).takeScreenShot("Step_4");
    }

}
