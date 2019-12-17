package PageObject.locators;

import PageObject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainLocators extends BasePage {
    public MainLocators(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//nav[@id='top-menu-nav']//a[text()='Kontakt']")
    protected WebElement btnKontakt;
}
