package utils;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.KeyEvent;

@Slf4j
@NoArgsConstructor
public class RobotUtils {

    public void robotSendCtrlVEnter() {
        Robot r = null;
        try {
            r = new Robot();
            Thread.sleep(1000);
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_V);
            Thread.sleep(3000);
            r.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(500);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_V);
            r.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(1000);
            log.info("Robot sent ctrl v enter");
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
            log.error("Robot error: " + e.toString());
        }
    }
}
