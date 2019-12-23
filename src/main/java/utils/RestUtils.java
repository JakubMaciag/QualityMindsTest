package utils;

import com.jayway.restassured.response.Response;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import static com.jayway.restassured.RestAssured.given;

@Slf4j
public class RestUtils {

    private int returnHTTPStatus(String baseUrl) {
        Response response = given()
                .relaxedHTTPSValidation()
                .request()
                .baseUri(baseUrl)
                .content("application/soap+xml; charset=UFT-8;")
                .get();
        log.info("HTTP ststus of:" + baseUrl + " is " + response.statusCode());
        return response.statusCode();
    }

    public boolean isPageStatus200(WebDriver driver) {
        Allure.addAttachment("Current page", driver.getCurrentUrl());
        int status = returnHTTPStatus(driver.getCurrentUrl());
        if (status == 200)
            return true;
        else
            return false;
    }
}
