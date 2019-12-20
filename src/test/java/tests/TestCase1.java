package tests;

import io.qameta.allure.Allure;
import org.apache.commons.lang.StringUtils;
import org.testng.Assert;
import pageObject.pagesAction.KontaktPage;
import pageObject.pagesAction.MainPage;
import org.testng.annotations.Test;
import utils.AttachUtils;
import utils.BaseTest;


public class TestCase1 extends BaseTest {
    private String sourceCodeKontakt1 = null;
    private String sourceCodeKontakt2 = null;

    @Test(description = "Open QM main page")
    public void openMainPage() {
        driver.get(propertiesUtils.getUrl());
        new MainPage(driver).verificationMainPageStuff();
        new AttachUtils(driver).takeScreenShot("Step_1");
    }

    @Test(description = "Open Kontakt page", dependsOnMethods = {"openMainPage"})
    public void openKontaktPage() {
        new MainPage(driver).clickBtnKontakt();
        sourceCodeKontakt1 = new KontaktPage(driver).verifyKontaktPageAndGetSourceCode();
        new AttachUtils(driver).takeScreenShot("Step_2");
    }

    @Test(description = "Verify email existance", dependsOnMethods = {"openKontaktPage"})
    public void verificationOfEmailExistance() {
        new KontaktPage(driver).verifyIfHelloEmailIsVisible();
        new AttachUtils(driver).takeScreenShot("Step_3");
    }

    @Test(description = "Return to QM main page", dependsOnMethods = {"verificationOfEmailExistance"})
    public void returnToMainPage() {
        driver.navigate().back();
        new MainPage(driver).verificationMainPageStuff();
        new AttachUtils(driver).takeScreenShot("Step_4");
    }

    @Test(description = "Open Kontakt page again", dependsOnMethods = {"returnToMainPage"})
    public void openKontaktPageAgain() {
        new MainPage(driver).clickBtnKontaktAndAnfahrt();
        sourceCodeKontakt2 = new KontaktPage(driver).verifyKontaktPageAndGetSourceCode();
        new AttachUtils(driver).takeScreenShot("Step_5");
    }

    @Test(description = "Verify Kontakt page content", dependsOnMethods = {"openKontaktPageAgain"})
    public void verificationOfKontaktPageContent() {
        Allure.addAttachment("Kontakt1",".html",sourceCodeKontakt1,".html");
        Allure.addAttachment("Kontakt2",".html",sourceCodeKontakt2,".html");
        Allure.attachment("Differences:",StringUtils.difference(sourceCodeKontakt1, sourceCodeKontakt2));
        Assert.assertTrue(StringUtils.equals(sourceCodeKontakt1, sourceCodeKontakt2));
        new AttachUtils(driver).takeScreenShot("Step_6");
    }
}
