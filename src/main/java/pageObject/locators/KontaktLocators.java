package pageObject.locators;

import pageObject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KontaktLocators extends BasePage {
    public KontaktLocators(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='Kontakt & Anfahrt']")
    protected WebElement labelKontaktAndAnfahrt;

    @FindBy(linkText = "hello@qualityminds.de")
    protected WebElement txtSpecifiedEmail_Hello;

}
