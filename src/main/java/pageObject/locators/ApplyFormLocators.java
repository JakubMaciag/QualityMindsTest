package pageObject.locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import pageObject.BasePage;

import java.util.List;

public class ApplyFormLocators extends BasePage {
    @FindBy(id = "job-ad-form-department")
    protected WebElement labelCareerApplicationForm;
    @FindBy(xpath = "//input[@type='submit' and @class='btn btn-default qm-button']")
    protected WebElement btnSubmitApplyNow;
    @FindBys({
            @FindBy(xpath = "//div[@class='row  first_row']//span[@class='parsley-required']"),
    })
    protected List<WebElement> msgMadatoryFieldsList;
    @FindBy(xpath = "//div[contains(@class,'contact-form-first-name')]//span[@class='parsley-required']")
    protected WebElement msgMandatoryFieldForFirstName;
    @FindBy(xpath = "//div[contains(@class,'contact-form-last-name')]//span[@class='parsley-required']")
    protected WebElement msgMandatoryFieldForLastName;
    @FindBy(xpath = "//label[text()='Email']/..//span[@class='parsley-required']")
    protected WebElement msgMandatoryFieldForEmail;
    @FindBy(xpath = "//div[contains(@class,'consent-field')]//span[@class='parsley-required']")
    protected WebElement msgMandatoryFieldForCheckboxDataProtection;
    @FindBy(xpath = "//a[@href='https://qualityminds.de/karriere/#job-ads']")
    protected WebElement btnBackToCareerPage;
    @FindBy(css = ".alert-success")
    protected WebElement labelAlertSuccesMsg;
    @FindBy(xpath = "//span[@class='parsley-type' and contains(text(),'Die Eingabe muss eine gültige E-Mail-Adresse sein.')]")
    protected WebElement msgIvalidEmail;
    @FindBy(xpath = "//div[contains(@class,'form-group contact-form-first-name')]//input")
    protected WebElement textFieldFirstName;
    @FindBy(xpath = "//div[contains(@class,'form-group contact-form-last-name')]//input")
    protected WebElement textFieldLastName;
    @FindBy(xpath = "//label[text()='Email']/..//input[@data-type='email']")
    protected WebElement textFieldEmail;
    @FindBy(css = ".option-required")
    protected WebElement checkBoxDataProtection;
    @FindBy(css = ".btn.btn-block")
    protected WebElement btnAttachFile;
    @FindBy(css = ".cf2-file-name.file-name")
    protected WebElement labelAttachedFileName;

    public ApplyFormLocators(WebDriver driver) {
        super(driver);
    }
}
