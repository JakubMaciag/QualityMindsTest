package utils;

import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Slf4j
public class AttachUtils {
    private WebDriver webDriver;
    public AttachUtils(WebDriver driver){
        this.webDriver=driver;
    }
    public void takeScreenShot(String additionalName) {
        String filePath = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS").format(Calendar.getInstance().getTime()) + ".png";
        log.info("ScreenShot has been saved as: " + System.getProperty("user.dir") + "\\target\\allure-results\\" + filePath);
        try {
            Allure.addAttachment(additionalName + "_" + filePath, new ByteArrayInputStream(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES)));
        } catch (NullPointerException e) {
            log.error("Error with attaching screenShot");
        }
    }
}
