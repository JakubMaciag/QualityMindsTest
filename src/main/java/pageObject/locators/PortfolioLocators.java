package pageObject.locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pageObject.BasePage;

import java.util.List;

public class PortfolioLocators extends BasePage {
    @FindBy(css = ".entry-title span")
    protected WebElement labelWebAutomationMobileTesting;
    @FindBy(xpath = "//ul[@id='top-menu']//a[@href='https://qualityminds.de/team_page/wam-testing/']")
    protected WebElement btnWebAutomationAndMobileInSubMenu;
    @FindAll({
            @FindBy(xpath = "//ul[@id='top-menu']//a[@href='https://qualityminds.de/team-overview/']/..//ul"),
            @FindBy(xpath = "//ul[@id='top-menu']//a[@href='https://qualityminds.de/team-overview/']/..//li/a")
    })
    protected List<WebElement> subMenuPortfolioList;
    @FindBy(linkText = "FLYER FIND THE BUG SESSION")
    protected WebElement btnFlyer;
    @FindBy(xpath = "//ul[@id='top-menu']//a[@href='https://qualityminds.de/team-overview/']")
    protected WebElement btnPortfolio;
    @FindBy(xpath = "//div[@id='team-tab-three-title-desktop']/div[text()='Mobile']")
    protected WebElement btnMobileContent;
    @FindBy(xpath = "//div[@id='team-tab-three-title-desktop']/..")
    protected WebElement underlineOfMobileContentButton;
    @FindBy(xpath = "//div[@id='team-tab-three-body']//p[contains(text(),'Du hast den Anspruch, eine qualitativ hochwertige Mobile App für deine User')]")
    protected WebElement textMobileContent;

    public PortfolioLocators(WebDriver driver) {
        super(driver);
    }
}
