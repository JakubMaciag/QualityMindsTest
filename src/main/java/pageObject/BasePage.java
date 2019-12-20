package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.RestUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class BasePage{

    protected WebDriver driver;

    @Getter(AccessLevel.PUBLIC)
    protected RestUtils restUtils;
    protected JSCommands jsCommands;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        restUtils = new RestUtils();
        jsCommands = new JSCommands(driver);
        PageFactory.initElements(driver, this);
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            log.error("Element: " + element + " was not found.");
            return false;
        }
    }

    protected boolean isEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            log.error("Element: " + element + " was not found.");
            return false;
        }
    }

    protected boolean isSelected(WebElement element) {
        try {
            return element.isSelected();
        } catch (Exception e) {
            log.error("Element: " + element + " was not found.");
            return false;
        }
    }

    protected void waitUntilElementIsClickable(WebElement element, int time, boolean assertError){
        try {
            WebDriverWait webDriverWait = new WebDriverWait(this.driver,time);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
            log.info("Wait for element until it is clickable");
        }catch (Exception e){
            log.error("Element was not found / is not clickable");
            if(assertError)
                Assert.fail("Element was not found / is not clickable");
        }
    }

    protected void waitUntilElementIsVisible(WebElement element, int time, boolean assertError){
        try {
            WebDriverWait webDriverWait = new WebDriverWait(this.driver,time);
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
            log.info("Wait for element until it is visible");
        }catch (Exception e){
            log.error("Element was not found / is not visible");
            if(assertError)
                Assert.fail("Element was not found / is not visible");
        }
    }

    protected void clickWebElement(WebElement element, int time, boolean wantWait, boolean assertError){
        try{
            if(wantWait)
                waitUntilElementIsClickable(element,time,assertError);
            if(isEnabled(element)&&isDisplayed(element))
                element.click();
            log.info("Element was clicked: "+element.toString());
        }catch (Exception e){
            log.error("Element was not clicked: "+element.toString());
            if (assertError)
                Assert.fail("Element was not clicked: "+element.toString());
        }
    }

    protected void fillTextField(WebElement element, String value, int time, boolean assertError){
        waitUntilElementIsClickable(element,time,assertError);
        if(isDisplayed(element)){
            clickWebElement(element, time, false, assertError);
            element.sendKeys(value);
            log.info("Text:'"+value+"' was send to txt input: "+element.toString());
        }else {
            if (assertError){
                log.error("Element was not found / was allowed to send txt");
                Assert.fail("Element was not found / was allowed to send txt");
            }else {
                log.warn("Element was not important");
            }
        }
    }

    protected String gePageHTMLSource(){
        return driver.getPageSource();
    }

    protected void waitUntilElementContainsText(WebElement element,String text, int time,boolean assertError){
        try{
            WebDriverWait waitTmp = new WebDriverWait(this.driver,time);
            waitTmp.until(ExpectedConditions.textToBePresentInElement(element,text));
            log.info("Element contains text");
        }catch (Exception e){
            log.error("Element does not contain text");
            if(assertError)
                Assert.fail("Element was not found / not contain text");
        }
    }

    protected void moveToElementActions(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        log.info("Move to element: "+element.toString());
    }

    protected void moveToElementAndClickActions(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        actions.click(element).build().perform();
        log.info("Move to and click element: "+element.toString());
    }

    protected void moveToElementAndFillTextActions(WebElement element,String text){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        actions.click(element).sendKeys(element,text).build().perform();
        log.info(" Move to, click and fill with text element: "+element.toString());
    }

    //todo
    protected void WaitUntilAttributeValueEquals(WebElement element, String attributeName, String attributeValue, int time)
    {
        try {
            WebDriverWait waitTmp = new WebDriverWait(this.driver, time);
//        waitTmp.Until<IWebElement>((d) =>
//            {
//        if (webElement.GetAttribute(attributeName) == attributeValue)
//        {
//            return webElement;
//        }
        }catch (Exception e){

        }
//        return null;
//                });
    }
}
