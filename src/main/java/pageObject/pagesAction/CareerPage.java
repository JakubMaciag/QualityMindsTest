package pageObject.pagesAction;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObject.JSCommands;
import pageObject.locators.CareerLocators;
import utils.TimeOuts;

@Slf4j
public class CareerPage extends CareerLocators {
    private String linkKarrierePage = "https://qualityminds.de/karriere/";
    private int offset = 200;

    public CareerPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click Karriere button")
    public CareerPage clickCareerBtn(boolean assertError) {
        clickWebElement(btnKarriere, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, true, assertError);
        log.info("Clicked button Karriere");
        return this;
    }

    @Step("Click Bewirb dich jetzt button")
    public CareerPage clickApplyNowBtn(boolean assertError) {
        jsCommands.jsScrollToPoint(btnApplyNow.getLocation().getX(), btnApplyNow.getLocation().getY() - offset);
        clickWebElement(btnApplyNow, TimeOuts.TIME_WAIT_FOR_ELEMENT_NORMAL, true, assertError);
        log.info("Clicked button Bewirb dich jetzt!");
        return this;
    }

    @Step("Verificaion of loading Karrier page")
    public CareerPage verifyLoadingCareerPage() {
        jsCommands.jsVerifyPageState();
        Assert.assertEquals(driver.getCurrentUrl(), linkKarrierePage);
        log.info("Page Karriere is loaded and url is correct");
        return this;
    }

    @Step("Verification if label Karriere exists")
    public CareerPage verifyLabelCareer(boolean assertError) {
        waitUntilElementIsVisible(labelKarriere, TimeOuts.TIME_WAIT_FOR_ELEMENT_LONG, assertError);
        log.info("Label Karriere is displayed");
        return this;
    }
}
