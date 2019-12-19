package tests;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.BaseTest;

public class TestCase2 extends BaseTest {

    @Test(description = "Open QM main page")
    @Parameters(value ={"browser"})
    public void openMainPage(@Optional("chrome") String browser) {
        System.out.println(browser);
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
