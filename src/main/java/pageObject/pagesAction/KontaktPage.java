package pageObject.pagesAction;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import pageObject.JSCommands;
import pageObject.locators.KontaktLocators;
import org.openqa.selenium.WebDriver;
import utils.TimeOuts;
@Slf4j
public class KontaktPage extends KontaktLocators {

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
    public KontaktPage saveKontaktPageSourceIntoFile(){

        return this;
    }
}
