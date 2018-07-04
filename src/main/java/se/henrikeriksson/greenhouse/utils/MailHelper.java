package se.henrikeriksson.greenhouse.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MailHelper {
    private final static Logger log = LoggerFactory.getLogger(MailHelper.class);

    private static boolean maillow = true;
    private static boolean mailhigh = false;
    private static String emailReceiver = "henrik.eriksson.android@gmail.com";

    public static void waterlowmail() {
        if (maillow) {
            log.info(String.format("Water is low, send email to: %s", emailReceiver));
            Runtime r = Runtime.getRuntime();
            try {
                r.exec("/home/pi/greenhouse/sendWaterLowMail.sh");
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            maillow=false;
            mailhigh=true;
        }
    }
    public static void waterhighmail() {
        if (mailhigh) {
            log.info(String.format("Filled up water tank!, send email to: %s", emailReceiver));
            Runtime r = Runtime.getRuntime();
            try {
                r.exec("/home/pi/greenhouse/sendWaterHighMail.sh");
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            mailhigh=false;
            maillow=true;
        }
    }
}
