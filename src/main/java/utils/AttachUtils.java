package utils;

import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Slf4j
public class AttachUtils {
    private WebDriver webDriver;

    public AttachUtils(WebDriver driver) {
        this.webDriver = driver;
    }

    public void takeScreenShot(String additionalName) {
        String filePath = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS").format(Calendar.getInstance().getTime()) + ".png";
        log.info("ScreenShot has been saved as: " + System.getProperty("user.dir") + "\\target\\allure-results\\" + filePath);
        try {
            Allure.addAttachment(additionalName + "_" + filePath, new ByteArrayInputStream(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES)));
        } catch (NullPointerException e) {
            log.error("Error with attaching screenShot" + e);
        }
    }

    public void clearDirectory(String path) {
        try {
            FileUtils.cleanDirectory(new File(path));
        } catch (IOException e) {
            log.error("Error with clean directory " + e.toString());
        }
    }

    public void verifyIfFileExist(String path, boolean assertError) {
        String pathDirectory = path;
        File file = new File(pathDirectory);
        try {
            Thread.sleep(TimeOuts.DOWNLOAD_DELAY);
        } catch (Exception e) {
            log.error("Error with waiting for downloaded file");
        }
        if (file.isFile()) {
            log.info("Directory exists" + file.getAbsolutePath());
        } else {
            log.error("Error with checking directory: " + pathDirectory);
            if (assertError)
                Assert.fail("Directory does not exist " + pathDirectory);
        }
    }
}
