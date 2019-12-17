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
    public static void takeScreenShot(WebDriver driver, String additionalName) {
        String filePath = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS").format(Calendar.getInstance().getTime()) + ".png";
        log.info("ScreenShot has been saved as: " + System.getProperty("user.dir") + "\\target\\allure-results\\" + filePath);
        Allure.addAttachment(additionalName+"_"+filePath, new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
    }
}
