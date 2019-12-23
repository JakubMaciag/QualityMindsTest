package pageObject.pagesAction;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObject.JSCommands;
import pageObject.locators.ApplyFormLocators;
import utils.AttachUtils;
import utils.RobotUtils;
import utils.TimeOuts;

@Slf4j
public class ApplyFormPage extends ApplyFormLocators {
    private String linkBewerbungsformular = "https://qualityminds.de/kontaktformular/";
    private String uploadFilePath = "\\src\\test\\resources\\cv.txt";
    private String fileName = "cv.txt";

    public ApplyFormPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verification of loading Bewerbungsformular page")
    public ApplyFormPage verifyLoadingApplyFormPage() {
        new JSCommands(driver).jsVerifyPageState();
        Assert.assertEquals(driver.getCurrentUrl(), linkBewerbungsformular);
        log.info("Page Bewerbungsformular is loaded and url is correct");
        return this;
    }

    @Step("Verification of existence elements on page")
    public ApplyFormPage verifyIfFormElementsExist(boolean assertError) {
        waitUntilElementIsVisible(labelCareerApplicationForm, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        jsCommands.jsScrollToElement(btnBackToCareerPage);
        waitUntilElementIsVisible(textFieldFirstName, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        waitUntilElementIsVisible(textFieldLastName, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        waitUntilElementIsVisible(textFieldEmail, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        waitUntilElementIsVisible(checkBoxDataProtection, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        waitUntilElementIsVisible(btnSubmitApplyNow, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        log.info("Elements of application form exist");
        return this;
    }

    @Step("Click on Jetzt bewerben button")
    public ApplyFormPage clickSubmitButton(boolean assertError) {
        clickWebElement(btnSubmitApplyNow, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, true, assertError);
        log.info("Clicked submit button");
        return this;
    }

    @Step("Verification if message is not be submitted")
    public ApplyFormPage verifyNotSendingMessage(boolean assertError) {
        jsCommands.jsScrollToElement(btnBackToCareerPage);
        if (waitUntilElementIsNotVisible(labelAlertSuccesMsg, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL))
            log.info("Msg is not submited");
        else {
            log.error("Msg was found / is visible");
            if (assertError)
                Assert.fail("Msg was found / is visible");
        }
        return this;
    }

    @Step("Verification if validation messages are displayed")
    public ApplyFormPage verifyIfValidationMessagesExist(boolean assertError) {
        Assert.assertEquals(msgMadatoryFieldsList.size(), 3);
        for (WebElement element : msgMadatoryFieldsList) {
            waitUntilElementIsVisible(element, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        }
        log.info("Mandatory fields messages exist");
        return this;
    }

    @Step("Verification if validation message is displayed only for email")
    public ApplyFormPage verifyIfValidationMessageExistsOnlyForEmail(boolean assertError) {
        Assert.assertEquals(msgMadatoryFieldsList.size(), 1);
        for (WebElement element : msgMadatoryFieldsList) {
            waitUntilElementIsVisible(element, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        }
        Assert.assertTrue(isDisplayed(msgMandatoryFieldForEmail));
        log.info("Mandatory field message exists only for email in apply form");
        return this;
    }

    @Step("Fill the Vorname text field")
    public ApplyFormPage fillFirstNameTextField(boolean assertError, String firstName) {
        fillTextField(textFieldFirstName, firstName, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        log.info("First name field is filled");
        return this;
    }

    @Step("Fill the Nachname text field")
    public ApplyFormPage fillLastNameTextField(boolean assertError, String lastName) {
        fillTextField(textFieldLastName, lastName, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        log.info("Last name field is filled");
        return this;
    }

    @Step("Fill the Email text field")
    public ApplyFormPage fillEmailTextField(boolean assertError, String email) {
        fillTextField(textFieldEmail, email, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        log.info("Email field is filled");
        return this;
    }

    @Step("Verification if ivalid value message is displayed for email")
    public ApplyFormPage verifyIfInvalidValueMessageExistsForEmail(boolean assertError) {
        Assert.assertTrue(isDisplayed(msgIvalidEmail));
        log.info("Invalid value message exists for email in apply form");
        return this;
    }


    @Step("Click DATEIEN HOCHLADEN button")
    public ApplyFormPage clickAttachFileButton(boolean assertError) {
        clickWebElement(btnAttachFile, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, true, assertError);
        log.info("Clicked attach file button");
        return this;
    }

    @Step("Attach file")
    public ApplyFormPage attachFile(boolean assertError) {
        new AttachUtils(driver).copyTextIntoClipboard(System.getProperty("user.dir") + uploadFilePath);
        new RobotUtils().robotSendCtrlVEnter();
        waitUntilAttributeContains(labelAttachedFileName, "innerHTML", fileName, TimeOuts.TIME_WAIT_FOR_ELEMENT_LONG, assertError);
        System.out.println(labelAttachedFileName.getAttribute("innerHTML"));
        log.info("Attach file");
        return this;
    }

    @Step("Check the checkbox for Datenschutzerklärung")
    public ApplyFormPage checkDataProtectionCheckBox(boolean assertError) {
        waitUntilElementIsClickable(checkBoxDataProtection, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        if (!isSelected(checkBoxDataProtection))
            clickWebElement(checkBoxDataProtection, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, false, assertError);
        log.info("Check box Datenschutzerklärung was clicked");
        return this;
    }

    @Step("Verification if checkbox for Datenschutzerklärung is checked")
    public ApplyFormPage verifyIfCheckBoxDataProtectionIsChecked(boolean assertError) {
        waitUntilElementIsClickable(checkBoxDataProtection, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        Assert.assertTrue(isSelected(checkBoxDataProtection));
        log.info("Checkbox Datenschutzerklärung is checked");
        return this;
    }
}
