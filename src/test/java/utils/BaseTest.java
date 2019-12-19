package utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

@Slf4j
public class BaseTest {
    public BaseTest() {
        super();
        driverSettings = new DriverSettings();
        attachUtils = new AttachUtils(getDriver());
    }

    @Getter(AccessLevel.PUBLIC)
    protected WebDriver driver;
    protected PropertiesUtils propertiesUtils;
    private DriverSettings driverSettings;
    private AttachUtils attachUtils;

    @BeforeTest()
    @Parameters(value = {"browserName"})
    public void beforeSuite(String browserName) {
        propertiesUtils = new PropertiesUtils();
        driver = driverSettings.runBrowser(browserName);
        log.info("Beginning of suite");
    }

    @AfterTest
    public void afterSuite() {
        driverSettings.closeBrowser(driver);
        log.info("End of suite");
    }

    @AfterMethod(alwaysRun = true)
    public void screenShotOnFailureAndSkip(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            attachUtils.takeScreenShot("Test failed/skipped - " + getTestMethodName(iTestResult));
            log.warn("Test failed/skipped");
        }
    }

    private String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
}
