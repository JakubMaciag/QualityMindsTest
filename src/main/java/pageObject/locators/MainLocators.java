package pageObject.locators;

import org.openqa.selenium.support.FindAll;
import pageObject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class MainLocators extends BasePage {
    public MainLocators(WebDriver driver) {
        super(driver);
    }

    @FindBys({
            @FindBy(css = "nav[id='top-menu-nav']"),
            @FindBy(css = "a[href='https://qualityminds.de/kontakt/']")
    })
    protected List<WebElement> btnKontaktList;

    @FindAll({
            @FindBy(xpath = "//ul[@id='top-menu']//a[@href='https://qualityminds.de/team-overview/']/..//ul"),
            @FindBy(xpath = "//ul[@id='top-menu']//a[@href='https://qualityminds.de/team-overview/']/..//li/a")
    })
    protected List<WebElement> subMenuPortfolioList;

    @FindBy(xpath = "//ul[@id='top-menu']//a[@href='https://qualityminds.de/team-overview/']")
    protected WebElement btnPortfolio;

    @FindBy(xpath = "//ul[@id='top-menu']//a[@href='https://qualityminds.de/team_page/wam-testing/']")
    protected WebElement btnWebAutomationAndMobileInSubMenu;

    @FindBy(xpath = "//ul[@id='menu-footer-menu']//a[@href='https://qualityminds.de/kontakt/']")
    protected WebElement btnKontaktAndAnfahrtFooterMenu;

    @FindBy(css = "#logo")
    protected WebElement imgLogo;

    @FindBy(xpath = "//ul[@id='top-menu']//a[@href='https://qualityminds.de/']/..")
    protected WebElement btnHome;
}
