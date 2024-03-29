package pageObject.pagesAction;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObject.JSCommands;
import pageObject.locators.ContactLocators;
import utils.TimeOuts;

import java.util.Arrays;
import java.util.List;


@Slf4j
public class ContactPage extends ContactLocators {
    @NonNull
    @Getter(AccessLevel.PUBLIC)
    private String sourceCode = null;
    private String link = "https://qualityminds.de/kontakt/";

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click Kontakt button")
    public ContactPage clickBtnKontakt(boolean assertError) {
        waitUntilElementIsClickable(btnKontaktList.get(0), TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        clickWebElement(btnKontaktList.get(0), TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, true, assertError);
        log.info("Clicked button Kontakt");
        return this;
    }

    @Step("Verification of loading Kontakt page")
    public ContactPage verifyLoadingKontaktPage() {
        jsCommands.jsVerifyPageState();
        Assert.assertEquals(driver.getCurrentUrl(), "https://qualityminds.de/kontakt/");
        log.info("Page Kontakt & Anfahrt is loaded and url is correct");
        return this;
    }

    @Step("Verification if Kontrakt & Anfahrt sign is displayed")
    public ContactPage verifyIfKontaktAndAnfahrtIsDisplayed(boolean assertError) {
        waitUntilElementIsVisible(labelKontaktAndAnfahrt, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        log.info("Sign Kontakt & Anfahrt is displayed");
        return this;
    }

    @Step("Save source HTML as a string - Kontakt page")
    public ContactPage saveKontaktPageSource() {
        sourceCode = gePageHTMLSource();
        return this;
    }

    @Step("Verification if specified email is visible")
    public ContactPage verifyIfHelloEmailIsVisible(boolean assertError) {
        jsCommands.jsScrollToElement(txtSpecifiedEmail_Hello);
        waitUntilElementContainsText(txtSpecifiedEmail_Hello, "hello@qualityminds.de",
                TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, assertError);
        Allure.attachment("Email", "hello@qualityminds.de");
        log.info("Verify if hello@qualityminds.de is visible");
        return this;
    }

    public List<String> verifyKontaktPageAndGetSourceCode(boolean assertError) {
        this.verifyLoadingKontaktPage()
                .verifyIfKontaktAndAnfahrtIsDisplayed(assertError)
                .saveKontaktPageSource();
        return Arrays.asList(link, driver.getCurrentUrl(), this.getSourceCode());
    }
}
