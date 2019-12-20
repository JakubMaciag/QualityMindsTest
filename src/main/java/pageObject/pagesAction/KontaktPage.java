package pageObject.pagesAction;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import pageObject.JSCommands;
import pageObject.locators.KontaktLocators;
import org.openqa.selenium.WebDriver;
import utils.TimeOuts;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class KontaktPage extends KontaktLocators {
    @Getter(AccessLevel.PUBLIC)
    private String tmp = null;

    public KontaktPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verificaion of loading Kontakt page")
    public KontaktPage verifyLoadingKontaktPage(){
        new JSCommands(driver).jsVerifyPageState();
        Assert.assertEquals(driver.getCurrentUrl(),"https://qualityminds.de/kontakt/");
        log.info("Page Kontakt & Anfahrt is loaded and url is correct");
        return this;
    }

    @Step("Verification if Kontrakt & Anfahrt sign is displayed")
    public KontaktPage verifyIfKontaktAndAnfahrtIsDisplayed(){
        waitUntilElementIsVisible(labelKontaktAndAnfahrt, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL,true);
        log.info("Sign Kontakt & Anfahrt is displayed");
        return this;
    }

    @Step("Save source HTML as a file - Kontakt page")
    public KontaktPage saveKontaktPageSource(){
        tmp = gePageHTMLSource();
        return this;
    }

    @Step("Verification if specified email is visible")
    public KontaktPage verifyIfHelloEmailIsVisible(){
        jsCommands.jsScrollToElement(txtSpecifiedEmail_Hello);
        waitUntilElementContainsText(txtSpecifiedEmail_Hello,"hello@qualityminds.de",TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL,true);
        Allure.attachment("Email","hello@qualityminds.de");
        log.info("Verify if hello@qualityminds.de is visible");
        return this;
    }

    public String verifyKontaktPageAndGetSourceCode(){
        return this.verifyLoadingKontaktPage()
                .verifyIfKontaktAndAnfahrtIsDisplayed()
                .saveKontaktPageSource().getTmp();
    }
}
