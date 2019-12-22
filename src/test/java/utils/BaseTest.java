package utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

@Slf4j
public class BaseTest {
    public BaseTest() {
        super();
        propertiesUtils = new PropertiesUtils();
        driverSettings = new DriverSettings(propertiesUtils);
        attachUtils = new AttachUtils(getDriver());
    }

    @Getter(AccessLevel.PUBLIC)
    protected WebDriver driver;
    protected PropertiesUtils propertiesUtils;
    @Getter(AccessLevel.PUBLIC)
    @NonNull
    protected String browserName;
    @Getter(AccessLevel.PUBLIC)
    @NonNull
    protected String downloadDirectory;
    private DriverSettings driverSettings;
    private AttachUtils attachUtils;

    private String setDirectoryByBrowser(String browser){
        if(browser.equals("chrome"))
            return System.getProperty("user.dir")+propertiesUtils.getDownloadDirecotryForChrome();
        else if(browser.equals("firefox"))
            return System.getProperty("user.dir")+propertiesUtils.getDownloadDirecotryForFirefox();
        else
            return null;
    }

    @BeforeTest()
    @Parameters(value = {"browserName"})
    public void setUp(String browserName) {
        this.browserName = browserName;
        downloadDirectory = setDirectoryByBrowser(browserName);
        driver = driverSettings.runBrowser(browserName);
        log.info("Beginning of suite");
    }

    @AfterTest
    public void tearDown() {
        driverSettings.closeBrowser(driver);
        log.info("End of test");
    }

    @AfterMethod(alwaysRun = true)
    public void screenShotOnFailureAndSkip(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            attachUtils.takeScreenShot("Test_failed/skipped - " + getTestMethodName(iTestResult));
            log.warn("Test failed/skipped");
        }
    }

    private String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
}
