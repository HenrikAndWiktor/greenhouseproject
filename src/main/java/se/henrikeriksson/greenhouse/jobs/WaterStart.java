package se.henrikeriksson.greenhouse.jobs;

import org.knowm.sundial.Job;
import org.knowm.sundial.annotations.CronTrigger;
import org.knowm.sundial.exceptions.JobInterruptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.henrikeriksson.greenhouse.utils.Radio433Utility;

import java.io.IOException;

// 0 0/5 13 * * ? execute start every 5 min during 1 hour from  13.
@CronTrigger(cron = "0 0/5 13 * * ?")
public class WaterStart extends Job {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public void doRun() throws JobInterruptException {
        Radio433Utility.startWater();
        /*try {
            log.info("Start watering");
            int code=1394005;
            log.info(String.format("code=%s", code));
            Runtime r = Runtime.getRuntime();
            r.exec(String.format("sudo /home/pi/433/wiringPi/433Utils/RPi_utils/codesend %s",code));
            log.info("Water pump activated");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}