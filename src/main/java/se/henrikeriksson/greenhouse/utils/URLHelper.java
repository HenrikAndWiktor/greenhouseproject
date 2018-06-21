package se.henrikeriksson.greenhouse.utils;

public class URLHelper {
    public static boolean ping(String dns, int packets) throws Exception {
        Process p = Runtime.getRuntime().exec("ping -c "+packets+" "+dns);
        return p.waitFor() == 0;
    }
}
