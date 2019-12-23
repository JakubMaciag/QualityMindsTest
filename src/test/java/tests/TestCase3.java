package tests;

import org.testng.annotations.Test;
import pageObject.pagesAction.ApplyFormPage;
import pageObject.pagesAction.CareerPage;
import pageObject.pagesAction.MainPage;
import utils.AttachUtils;
import utils.BaseTest;

public class TestCase3 extends BaseTest {
    private boolean assertError = true;

    @Test(description = "Open QM main page")
    public void openMainPage() {
        driver.get(propertiesUtils.getUrl());
        new MainPage(driver).verificationMainPageStuff(propertiesUtils.getUrl(),assertError);
        new AttachUtils(driver).takeScreenShot("Step_1");
    }

    @Test(description = "Open Karriere page", dependsOnMethods = {"openMainPage"})
    public void openCareerPage() {
        new CareerPage(driver).clickCareerBtn(assertError)
                .verifyLoadingCareerPage()
                .verifyLabelCareer(assertError);
        new AttachUtils(driver).takeScreenShot("Step_2");
    }

    @Test(description = "Click Bewirb dich jetzt button and verify apply now page",
            dependsOnMethods = {"openCareerPage"})
    public void clickApplyNowBtnAndVerifyPageApplyForm() {
        new CareerPage(driver).clickApplyNowBtn(assertError);
        new ApplyFormPage(driver).verifyLoadingApplyFormPage().verifyIfFormElementsExist(assertError);
        new AttachUtils(driver).takeScreenShot("Step_3");
    }

    @Test(description = "Click on Jetzt bewerben button and verify if message is not be submitted",
            dependsOnMethods = {"clickApplyNowBtnAndVerifyPageApplyForm"})
    public void clickApplyFormButtonAndVerifyNotSendingForm() {
        new ApplyFormPage(driver).clickSubmitButton(assertError).verifyNotSendingMessage(assertError);
        new AttachUtils(driver).takeScreenShot("Step_4");
    }

    @Test(description = "Verification if validation messages are displayed",
            dependsOnMethods = {"clickApplyFormButtonAndVerifyNotSendingForm"})
    public void verifyIfValidationMessagesAreDisplayed() {
        new ApplyFormPage(driver).verifyIfValidationMessagesExist(assertError);
        new AttachUtils(driver).takeScreenShot("Step_5");
    }

    @Test(description = "Fill the Vorname and Nachname fields",
            dependsOnMethods = {"verifyIfValidationMessagesAreDisplayed"})
    public void fillFirstAndLastNameTextFields() {
        new ApplyFormPage(driver).fillFirstNameTextField(assertError, "test")
                .fillLastNameTextField(assertError, "test");
        new AttachUtils(driver).takeScreenShot("Step_6");
    }

    @Test(description = "Click on Jetzt bewerben button and verify if message is not be submitted",
            dependsOnMethods = {"fillFirstAndLastNameTextFields"})
    public void clickApplyFormButtonAndVerifyNotSendingFormSecondTime() {
        new ApplyFormPage(driver).clickSubmitButton(assertError).verifyNotSendingMessage(assertError);
        new AttachUtils(driver).takeScreenShot("Step_7");
    }

    @Test(description = "Verification if validation message is displayed only for Email",
            dependsOnMethods = {"clickApplyFormButtonAndVerifyNotSendingFormSecondTime"})
    public void verifyIfValidationMessageIsDisplayedOnlyForEmail() {
        new ApplyFormPage(driver).verifyIfValidationMessageExistsOnlyForEmail(assertError);
        new AttachUtils(driver).takeScreenShot("Step_8");
    }

    @Test(description = "Fill the E-mail field with an Invalid value",
            dependsOnMethods={"verifyIfValidationMessageIsDisplayedOnlyForEmail"})
    public void fillEmailTextFieldWithInvalidValue(){
        new ApplyFormPage(driver).fillEmailTextField(assertError,"test")
                .verifyIfInvalidValueMessageExistsForEmail(assertError);
        new AttachUtils(driver).takeScreenShot("Step_9");
    }

    @Test(description = "Click on Jetzt bewerben button and verify if message is not be submitted",
            dependsOnMethods = {"fillEmailTextFieldWithInvalidValue"})
    public void clickApplyFormButtonAndVerifyNotSendingFormThirdTime() {
        new ApplyFormPage(driver).clickSubmitButton(assertError).verifyNotSendingMessage(assertError);
        new AttachUtils(driver).takeScreenShot("Step_10");
    }

    @Test(description = "Attach file with DATEIEN HOCHLADEN button and verify if filename of attachment is displayed above",
            dependsOnMethods = {"clickApplyFormButtonAndVerifyNotSendingFormThirdTime"})
    public void clickAttachFileButtonAndVerivyAttaching() {
        new ApplyFormPage(driver).clickAttachFileButton(assertError).attachFile(assertError);
        new AttachUtils(driver).takeScreenShot("Step_11");
    }

    @Test(description = "Check the checkbox for Datenschutzerklärung and verification if is checked",
    dependsOnMethods = {"clickAttachFileButtonAndVerivyAttaching"})
    public void checkDataProtectionCheckboxAndVerifyIfIsChecked(){
new ApplyFormPage(driver).checkDataProtectionCheckBox(assertError)
.verifyIfCheckBoxDataProtectionIsChecked(assertError);
        new AttachUtils(driver).takeScreenShot("Step_12");
    }
}
