package se.henrikeriksson.greenhouse.jobs;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.knowm.sundial.Job;
import org.knowm.sundial.SundialJobScheduler;
import org.knowm.sundial.annotations.CronTrigger;
import org.knowm.sundial.exceptions.JobInterruptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.henrikeriksson.greenhouse.utils.Radio433Utility;

/**
 * Created by henrikeriksson on 2017-06-11.
 */
@CronTrigger(cron = "0/30 * * * * ?")
public class BackgroundJob extends Job {

    private final Logger logger = LoggerFactory.getLogger(BackgroundJob.class);

    @Override
    public void doRun() throws JobInterruptException {

          logger.info("Is water on "+Radio433Utility.isWaterOn);
    }
}