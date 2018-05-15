package se.henrikeriksson.greenhouse.jobs;

import org.knowm.sundial.Job;
import org.knowm.sundial.annotations.CronTrigger;
import org.knowm.sundial.exceptions.JobInterruptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@CronTrigger(cron = "0 0 18 * * ?")
public class WaterShut extends Job {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public void doRun() throws JobInterruptException {
        try {
            log.info("Shut off watering");
            int code=1394004;
            log.info(String.format("code=%s", code));
            Runtime r = Runtime.getRuntime();
            r.exec(String.format("sudo /home/pi/433/wiringPi/433Utils/RPi_utils/codesend %s",code));
            log.info("Water pump deactivated");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
