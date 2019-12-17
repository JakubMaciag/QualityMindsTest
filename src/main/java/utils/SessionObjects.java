package utils;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import java.util.HashMap;

@Slf4j
public class SessionObjects {
    private static final ThreadLocal<HashMap<String, Object>> sessionObjects = ThreadLocal.withInitial(HashMap::new);

    private static final String BROWSER_NAME = "BROWSER_NAME";

    private static void setObj(String name, Object data) {
        synchronized (sessionObjects) {
            Assert.assertNotNull(data, name + " is null. Trying to set null value to SessionObjects");
            log.info("Set object: " + name + " with data: " + data);
            sessionObjects.get().put(name, data);
        }
    }

    private static Object getObj(String name) {
        synchronized (sessionObjects) {
            Object obj = sessionObjects.get().get(name);
            Assert.assertNotNull(obj, name + " wasn't set to SessionObjects");
            log.info("Get object: " + name);
            return obj;
        }
    }

    private static String getString(String name) {
        return (String) getObj(name);
    }

    private static void setString(String name, String value) {
        Assert.assertFalse(value.isEmpty(), name + " is empty. Trying to set empty  string to SessionObjects.");
        setObj(name, value.trim());
    }

    public static void setBrowserName(String breowserName) {
        setString(BROWSER_NAME, breowserName);
    }

    public static String getBrowserName() {
        return getString(BROWSER_NAME);
    }

}
