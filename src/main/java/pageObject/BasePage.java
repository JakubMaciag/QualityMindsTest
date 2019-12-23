package pageObject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.RestUtils;

@Slf4j
public class BasePage {

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

    protected void clickWebElement(WebElement element, int time, boolean wantWait, boolean assertError) {
        try {
            if (wantWait)
                waitUntilElementIsClickable(element, time, assertError);
            if (isEnabled(element) && isDisplayed(element))
                element.click();
            log.info("Element was clicked: " + element.toString());
        } catch (Exception e) {
            log.error("Element was not clicked: " + element.toString());
            if (assertError)
                Assert.fail("Element was not clicked: " + element.toString());
        }
    }

    protected void fillTextField(WebElement element, String value, int time, boolean assertError) {
        waitUntilElementIsClickable(element, time, assertError);
        if (isDisplayed(element)) {
            clickWebElement(element, time, false, assertError);
            element.sendKeys(value);
            log.info("Text:'" + value + "' was sent to txt input: " + element.toString());
        } else {
            if (assertError) {
                log.error("Element was not found / was allowed to send txt");
                Assert.fail("Element was not found / was allowed to send txt");
            } else {
                log.warn("Element was not important");
            }
        }
    }

    protected String gePageHTMLSource() {
        return driver.getPageSource();
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

    protected void moveToElementActions(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        log.info("Moved to element: " + element.toString());
    }

    protected void moveToElementAndClickActions(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        actions.click(element).build().perform();
        log.info("Moved to and clicked element: " + element.toString());
    }

    protected void clickActions(WebElement element) {
        Actions actions = new Actions(driver);
        actions.click(element).build().perform();
        log.info("Clicked element: " + element.toString());
    }

    protected void moveToElementAndFillTextActions(WebElement element, String text) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        actions.click(element).sendKeys(element, text).build().perform();
        log.info(" Moved to, clicked and filled with text element: " + element.toString());
    }

    protected void verifyIfPageIsLoaded() {
        Assert.assertTrue(restUtils.isPageStatus200(driver));
        jsCommands.jsVerifyPageState();
    }

    protected void waitUntilElementIsClickable(WebElement element, int time, boolean assertError) {
        try {
            log.info("Wait for element until it is clickable");
            WebDriverWait webDriverWait = new WebDriverWait(this.driver, time);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            log.error("Element was not found / is not clickable");
            if (assertError)
                Assert.fail("Element was not found / is not clickable");
        }
    }

    protected void waitUntilElementIsVisible(WebElement element, int time, boolean assertError) {
        try {
            log.info("Wait for element until it is visible");
            WebDriverWait webDriverWait = new WebDriverWait(this.driver, time);
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            log.error("Element was not found / is not visible");
            if (assertError)
                Assert.fail("Element was not found / is not visible");
        }
    }

    protected boolean waitUntilElementIsNotVisible(WebElement element, int time) {
        log.info("Wait for element is it not visible");
        WebDriverWait webDriverWait = new WebDriverWait(this.driver, time);
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    protected void waitUntilElementContainsText(WebElement element, String text, int time, boolean assertError) {
        try {
            log.info("Waiting for text containded by element");
            WebDriverWait waitTmp = new WebDriverWait(this.driver, time);
            waitTmp.until(ExpectedConditions.textToBePresentInElement(element, text));
            log.info("Element contains text");
        } catch (Exception e) {
            log.error("Element does not contain text");
            if (assertError)
                Assert.fail("Element was not found / not contain text");
        }
    }

    protected void waitUntilCSSValueContains(WebElement element, String cssValueName, String cssValue, int time, boolean assertError) {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, time);
            log.info("Wait until css attribute:" + cssValueName + " contains:" + cssValue);
            wait.until((ExpectedCondition<Boolean>) driver -> {
                if (element.getCssValue(cssValueName).contains(cssValue)) return Boolean.TRUE;
                return null;
            });
        } catch (Exception e) {
            log.error("Element was not found /does not contains value");
            if (assertError)
                Assert.fail("Element was not found /does not contains value");
        }
    }

    protected void waitUntilAttributeContains(WebElement element, String attribute, String value, int time, boolean assertError) {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, time);
            log.info("Wait until attribute:" + attribute + " contains:" + value);
            wait.until((ExpectedCondition<Boolean>) driver -> {
                if (element.getAttribute(attribute).contains(value)) return Boolean.TRUE;
                return null;
            });
        } catch (Exception e) {
            log.error("Element was not found /does not contains value");
            if (assertError)
                Assert.fail("Element was not found /does not contains value");
        }
    }

}
