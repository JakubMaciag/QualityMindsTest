package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class JSCommands {
    private WebDriver driver;
    public JSCommands(WebDriver driver)
    {
        this.driver=driver;
    }
    @Step("Scroll up")
    public void jsScrollUp(){((JavascriptExecutor)driver).executeScript("window.scrollTo(0,0)");}

    @Step("Scroll down")
    public void jsScrollDown(){((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");}

    @Step("Scroll to element")
    public void jsScrollToElement(WebElement element){((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);}

}
