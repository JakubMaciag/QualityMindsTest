package pageObject.locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.BasePage;

public class CareerLocators extends BasePage {
    @FindBy(xpath = "//a[@href='https://qualityminds.de/karriere/']")
    protected WebElement btnKarriere;
    @FindBy(xpath = "//p[contains(.,'Karriere')]")
    protected WebElement labelKarriere;
    @FindBy(xpath = "//a[@href='https://qualityminds.de/kontaktformular/']")
    protected WebElement btnApplyNow;

    public CareerLocators(WebDriver driver) {
        super(driver);
    }
}
