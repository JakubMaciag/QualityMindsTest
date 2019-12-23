package pageObject;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

@Slf4j
public class JSCommands {
    private WebDriver driver;

    public JSCommands(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Scroll up")
    public void jsScrollUp() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
        log.info("Scrolled up");
    }

    @Step("Scroll down")
    public void jsScrollDown() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        log.info("Scrolled down");
    }

    @Step("Scroll to element")
    public void jsScrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        log.info("Scrolled to element:" + element.toString());
    }

    @Step("Verify if page is loaded")
    public void jsVerifyPageState() {
        Assert.assertEquals(((JavascriptExecutor) driver).executeScript("return document.readyState").toString().trim(),
                "complete");
        log.info("Page state is complete: " + driver.getCurrentUrl());
    }

    @Step("Scroll down - to point")
    public void jsScrollToPoint(int x, int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(" + x + ", " + y + ")");
        log.info("Scrolled to point");
    }
}
