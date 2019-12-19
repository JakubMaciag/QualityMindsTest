package tests;

import org.testng.Assert;
import pageObject.JSCommands;
import pageObject.pagesAction.KontaktPage;
import pageObject.pagesAction.MainPage;
import org.testng.annotations.Test;
import utils.BaseTest;


public class TestCase1 extends BaseTest {

    @Test(description = "Open QM main page")
    public void openMainPage() {
        driver.get(propertiesUtils.getUrl());
        new JSCommands(driver).jsVerifyPageState();
        new MainPage(driver)
                .verifyIfURLisCorrect()
                .verifyLogo();
    }

    @Test(description = "Open Kontakt page", dependsOnMethods = {"openMainPage"})
    public void openKontaktPage() {
        new MainPage(driver).clickBtnKontakt();
        new KontaktPage(driver)
                .verifyLoadingKontaktPage()
                .verifyIfKontaktAndAnfahrtIsDisplayed()
                .saveKontaktPageSourceIntoFile();
    }

    @Test(description = "Verify email existance", dependsOnMethods = {"openKontaktPage"})
    public void verificationOfEmailExistance() {
        System.out.println("3");
    }

    @Test(description = "Return to QM main page", dependsOnMethods = {"verificationOfEmailExistance"})
    public void returnToMainPage() {
        System.out.println("4");
    }

    @Test(description = "Open Kontakt page again", dependsOnMethods = {"returnToMainPage"})
    public void openKontaktPageAgain() {
        System.out.println("5");
    }

    @Test(description = "Verify Kontakt page content", dependsOnMethods = {"openKontaktPageAgain"})
    public void verificationOfKontaktPageContent() {
        System.out.println("6");
    }
}
