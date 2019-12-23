package utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Slf4j
public class PropertiesUtils {
    private static final String PROPERTIES_FILE = "/config.properties";
    private static final String URL = "url";
    private static final String DOWNLOAD_DIRECTORY_CHROME = "linkForChrome";
    private static final String DOWNLOAD_DIRECTORY_FIREFOX = "linkForFirefox";

    @Getter(AccessLevel.PUBLIC)
    @NonNull
    private String url;
    @Getter(AccessLevel.PUBLIC)
    @NonNull
    private String downloadDirecotryForChrome;
    @Getter(AccessLevel.PUBLIC)
    @NonNull
    private String downloadDirecotryForFirefox;

    public PropertiesUtils() {
        loadSettingsDataFromProperties();
    }

    private void loadSettingsDataFromProperties() {
        Properties properties = getPropertiesFromFile();
        url = properties.getProperty(URL);
        downloadDirecotryForChrome = properties.getProperty(DOWNLOAD_DIRECTORY_CHROME);
        downloadDirecotryForFirefox = properties.getProperty(DOWNLOAD_DIRECTORY_FIREFOX);
        log.info("Properties variable assignment");
    }

    private Properties getPropertiesFromFile() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = PropertiesUtils.class.getResourceAsStream(PROPERTIES_FILE);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            properties.load(bufferedReader);
            log.info("Load data from properties file");
        } catch (IOException e) {
            log.error("Error with loading data from properties");
            throw new RuntimeException(e);
        }
        return properties;
    }
}
