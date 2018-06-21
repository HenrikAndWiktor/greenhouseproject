package se.henrikeriksson.greenhouse.utils;

import java.io.IOException;

public class MailHelper {
    private static boolean maillow = true;
    private static boolean mailhigh = false;
    public static void waterlowmail() {
        if (maillow) {
            Runtime r = Runtime.getRuntime();
            try {
                r.exec("echo -e \"Subject: Tank\n\nALERT: Water low in tank. You will get a mail when you fix the water tank.\" | sendmail -v henrik.eriksson.android@gmail.com");
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
            Runtime r = Runtime.getRuntime();
            try {
                r.exec("echo -e \"Subject: Tank\n\nFilled up water tank!\" | sendmail -v henrik.eriksson.android@gmail.com");
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            mailhigh=false;
            maillow=true;
        }
    }
}
