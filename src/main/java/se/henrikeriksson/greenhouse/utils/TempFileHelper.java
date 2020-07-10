package se.henrikeriksson.greenhouse.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

public class TempFileHelper {
    private static final Logger log = LoggerFactory.getLogger(TempFileHelper.class);

    public static  Double getTemperatureFromFile(String filePath) {
        Double result = null;
        try {
            java.util.Scanner scan = new java.util.Scanner(new java.io.File(filePath));
            scan.nextLine();
            String temp = scan.nextLine().split("=")[1];
            temp = new StringBuilder(temp).insert(temp.length()-3, ".").toString();
            result = Double.parseDouble(temp);
        } catch (FileNotFoundException fnfe) {
            log.error("Couldn't read temperature file: {}", filePath);
        }
        return result;
    }
}
