package se.henrikeriksson.greenhouse.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLHelper {
    public static boolean pingGET(String url,int timeout) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setConnectTimeout(timeout*1000);
        con.setReadTimeout(timeout*1000);
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept-Encoding", "musixmatch");
        con.connect();
        return String.valueOf(con.getResponseCode()).toCharArray()[0]=='2'||String.valueOf(con.getResponseCode()).toCharArray()[0]=='3';
    }
    public static boolean pingPOST(String url,int timeout) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setConnectTimeout(timeout*1000);
        con.setReadTimeout(timeout*1000);
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Encoding", "musixmatch");
        con.connect();
        return String.valueOf(con.getResponseCode()).toCharArray()[0]=='2'||String.valueOf(con.getResponseCode()).toCharArray()[0]=='3';
    }
}
