package se.henrikeriksson.greenhouse.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.henrikeriksson.greenhouse.GreenHouseApplication;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class MailHelper {
    private final static Logger log = LoggerFactory.getLogger(MailHelper.class);

    private static boolean maillow = true;
    private static boolean mailhigh = false;
    private static String emailReceiver = "henrik.eriksson.android@gmail.com";
    private static String emailFrom = "";

    public static void waterlowmail() {
        if (maillow) {
            log.info(String.format("Water is low, send email to: %s", emailReceiver));
            sendMail("Water is low");
            /*Runtime r = Runtime.getRuntime();
            try {
                r.exec("/home/pi/greenhouse/sendWaterLowMail.sh");
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }*/
            maillow=false;
            mailhigh=true;
        }
    }
    public static void waterhighmail() {
        if (mailhigh) {
            log.info(String.format("Filled up water tank!, send email to: %s", emailReceiver));
            sendMail("Filled up water tank!");
            /*Runtime r = Runtime.getRuntime();
            try {
                r.exec("/home/pi/greenhouse/sendWaterHighMail.sh");
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }*/
            mailhigh=false;
            maillow=true;
        }
    }

    public static void sendMail(String message) {

        final String username = GreenHouseApplication.myconfig.getMailUser();
        final String password = GreenHouseApplication.myconfig.getMailPassword();

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(username));
            mimeMessage.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("henrik.eriksson.android@gmail.com")
            );
            mimeMessage.setSubject("Greenhouse - event");
            mimeMessage.setText(message);

            Transport.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
