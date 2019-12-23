package pageObject.locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.BasePage;

public class MainLocators extends BasePage {
    @FindBy(xpath = "//ul[@id='menu-footer-menu']//a[@href='https://qualityminds.de/kontakt/']")
    protected WebElement btnKontaktAndAnfahrtFooterMenu;
    @FindBy(css = "#logo")
    protected WebElement imgLogo;
    @FindBy(xpath = "//ul[@id='top-menu']//a[@href='https://qualityminds.de/']")
    protected WebElement btnHome;
    @FindBy(xpath = "//ul[@id='top-menu']//a[@href='https://qualityminds.de/']/..")
    protected WebElement btnHomeBackground;
    @FindBy(css = ".cc-allow")
    protected WebElement btnAcceptCookies;

    public MainLocators(WebDriver driver) {
        super(driver);
    }
}
