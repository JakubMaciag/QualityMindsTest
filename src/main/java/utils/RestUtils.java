package utils;

import com.jayway.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import static com.jayway.restassured.RestAssured.given;

@Slf4j
public class RestUtils {
    private String toString(int status){return String.valueOf(status);}
    private int returnHTTPStatus(String baseUrl){
        Response response = given()
                .relaxedHTTPSValidation()
                .request()
                .baseUri(baseUrl)
                .content("application/soap+xml; charset=UFT-8;")
                .get();
        log.info("HTTP ststus of:"+baseUrl+" is "+response.statusCode());
        return response.statusCode();
    }

//    public boolean isPageStatus200
}
