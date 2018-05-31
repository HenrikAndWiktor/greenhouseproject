package se.henrikeriksson.greenhouse.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Radio433Utility {
    private final static Logger log = LoggerFactory.getLogger(Radio433Utility.class);

    public static boolean isWaterOn = false;

    public static void startWater() {
        try {
            log.info("Start watering");
            codeSend(1394005);
            log.info("Water pump activated");
            isWaterOn = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopWater() {
        try {
            log.info("Shut off watering");
            codeSend(1394004);
            log.info("Water pump deactivated");
            isWaterOn = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void codeSend(int code) throws IOException {
        log.info(String.format("code=%s", code));
        Runtime r = Runtime.getRuntime();
        r.exec(String.format("sudo /home/pi/433/wiringPi/433Utils/RPi_utils/codesend %s",code));
    }
}
