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
                r.exec(String.format("echo -e \"Subject: Tank\\n\\nALERT: Water low in tank. You will get a mail when you fix the water tank.\" | sendmail -v %s", emailReceiver));
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
                r.exec(String.format("echo -e \"Subject: Tank\\n\\nFilled up water tank!\" | sendmail -v %s", emailReceiver));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            mailhigh=false;
            maillow=true;
        }
    }
}
