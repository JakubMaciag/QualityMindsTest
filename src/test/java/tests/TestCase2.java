package tests;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.pagesAction.MainPage;
import utils.AttachUtils;
import utils.BaseTest;

public class TestCase2 extends BaseTest {

    @Test(description = "Open QM main page")
    public void openMainPage() {
        driver.get(propertiesUtils.getUrl());
        new MainPage(driver).verificationMainPageStuff();
        new AttachUtils(driver).takeScreenShot("Step_1");
    }

    @Test(description = "Open Kontakt page", dependsOnMethods = {"openMainPage"})
    public void openKontaktPage() {
        System.out.println("21");
    }
    @Test(description = "Verify email existance", dependsOnMethods = {"openKontaktPage"})
    public void verificationOfEmailExistance() {
        System.out.println("31");
    }
    @Test(description = "Return to QM main page", dependsOnMethods = {"verificationOfEmailExistance"})
    public void returnToMainPage() {
        System.out.println("41");
    }
    @Test(description = "Open Kontakt page again", dependsOnMethods = {"returnToMainPage"})
    public void openKontaktPageAgain() {
        System.out.println("51");
    }
    @Test(description = "Verify Kontakt page content", dependsOnMethods = {"openKontaktPageAgain"})
    public void verificationOfKontaktPageContent() {
        System.out.println("61");
    }
}
