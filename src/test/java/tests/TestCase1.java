package tests;

import io.qameta.allure.Allure;
import org.apache.commons.lang.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.pagesAction.ContactPage;
import pageObject.pagesAction.MainPage;
import utils.AttachUtils;
import utils.BaseTest;

import java.util.List;


public class TestCase1 extends BaseTest {
    private List<String> page1 = null;
    private List<String> page2 = null;
    private boolean assertError = true;

    @Test(description = "Open QM main page", groups = "loadMainPage")
    public void openMainPage() {
        driver.get(propertiesUtils.getUrl());
        new MainPage(driver).verificationMainPageStuff(propertiesUtils.getUrl(), assertError);
        new AttachUtils(driver).takeScreenShot("Step_1");
    }

    @Test(description = "Open Kontakt page", dependsOnMethods = {"openMainPage"})
    public void openKontaktPage() {
        page1 = new ContactPage(driver).clickBtnKontakt(assertError).verifyKontaktPageAndGetSourceCode(assertError);
        new AttachUtils(driver).takeScreenShot("Step_2");
    }

    @Test(description = "Verify email existence", dependsOnMethods = {"openKontaktPage"})
    public void verificationOfEmailExistence() {
        new ContactPage(driver).verifyIfHelloEmailIsVisible(assertError);
        new AttachUtils(driver).takeScreenShot("Step_3");
    }

    @Test(description = "Return to QM main page", dependsOnMethods = {"verificationOfEmailExistence"})
    public void returnToMainPage() {
        driver.navigate().back();
        new MainPage(driver).verificationMainPageStuff(propertiesUtils.getUrl(), assertError);
        new AttachUtils(driver).takeScreenShot("Step_4");
    }

    @Test(description = "Open Kontakt page again", dependsOnMethods = {"returnToMainPage"})
    public void openKontaktPageAgain() {
        new MainPage(driver).clickBtnKontaktAndAnfahrt(assertError);
        page2 = new ContactPage(driver).verifyKontaktPageAndGetSourceCode(assertError);
        new AttachUtils(driver).takeScreenShot("Step_5");
    }

    @Test(description = "Verify Kontakt page content", dependsOnMethods = {"openKontaktPageAgain"})
    public void verificationOfKontaktPageContent() {
        Allure.addAttachment("Kontakt1", ".html", page1.get(2), ".html");
        Allure.addAttachment("Kontakt2", ".html", page2.get(2), ".html");
        Allure.attachment("Differences:", StringUtils.difference(page1.get(2), page2.get(2)));
        //Basic assert is verification of link to web site
        Assert.assertTrue(StringUtils.equals(page1.get(1), page2.get(1)));
        //In HTML comparison pages rather always are different - here should be comparison of specified elements
        // Also it is possible to verify two screen shots of pages
        new SoftAssert().assertTrue(StringUtils.equals(page1.get(2), page2.get(2)));
        new AttachUtils(driver).takeScreenShot("Step_6");
    }
}
