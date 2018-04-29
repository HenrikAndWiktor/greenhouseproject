package se.henrikeriksson.greenhouse.jobs;

import org.knowm.sundial.Job;
import org.knowm.sundial.annotations.CronTrigger;
import org.knowm.sundial.exceptions.JobInterruptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@CronTrigger(cron = "0/5 * * * * ?")
public class FrostGuardJob extends Job {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public void doRun() throws JobInterruptException {
        try {
            log.info("start");
            int codeon=1397077,codeof=1397076;
            double low=5,high=8;
            log.info("codes;on=%s,off=%s,low=%s,high=%s",codeon,codeof,low,high);
            Runtime r = Runtime.getRuntime();
            java.util.Scanner scan = new java.util.Scanner(new java.io.File("/sys/bus/w1/devices/28-000007105c6f/w1_slave"));
            scan.nextLine();
            String stemp = scan.nextLine().split("=")[1];
            log.info("got stemp=%s",stemp);
            stemp = new StringBuilder(stemp).insert(stemp.length()-3, ".").toString();
            double temp = Double.parseDouble(stemp);
            log.info("stemp to temp=%s",temp);
            if (temp<low) {
                r.exec(String.format("sudo /home/pi/433/wiringPi/433Utils/RPi_utils/codesend %s",codeon));
                log.info("on");
            }
            if (temp>high) {
                r.exec(String.format("sudo /home/pi/433/wiringPi/433Utils/RPi_utils/codesend %s",codeof));
                log.info("off");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
