package pageObject.locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import pageObject.BasePage;

import java.util.List;

public class ContactLocators extends BasePage {
    @FindBys({
            @FindBy(css = "nav[id='top-menu-nav']"),
            @FindBy(css = "a[href='https://qualityminds.de/kontakt/']")
    })
    protected List<WebElement> btnKontaktList;
    @FindBy(xpath = "//span[text()='Kontakt & Anfahrt']")
    protected WebElement labelKontaktAndAnfahrt;
    @FindBy(linkText = "hello@qualityminds.de")
    protected WebElement txtSpecifiedEmail_Hello;

    public ContactLocators(WebDriver driver) {
        super(driver);
    }
}
