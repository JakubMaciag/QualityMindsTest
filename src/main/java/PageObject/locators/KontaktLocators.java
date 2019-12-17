package PageObject.locators;

import PageObject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KontaktLocators extends BasePage {
    public KontaktLocators(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='Kontakt & Anfahrt']")
    protected WebElement labelKontaktAndAnfahrt;

    @FindBy(xpath = "//span[text()='Office Nürnberg']")
    protected WebElement labelOfficeNurnberg;

    @FindBy(xpath = "//span[text()='Office München']")
    protected WebElement labelOfficeMunchen;

    @FindBy(xpath = "//span[text()='Office Warschau']")
    protected WebElement labelOfficeWarschau;

    @FindBy(xpath = "//a[@class='mailto-link']")
    protected WebElement txtSpecifiedEmail;

}
