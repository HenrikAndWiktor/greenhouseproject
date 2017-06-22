package se.henrikeriksson.greenhouse.jobs;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.knowm.sundial.Job;
import org.knowm.sundial.SundialJobScheduler;
import org.knowm.sundial.annotations.CronTrigger;
import org.knowm.sundial.exceptions.JobInterruptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by henrikeriksson on 2017-06-11.
 */
@CronTrigger(cron = "0/30 * * * * ?")
public class BackgroundJob extends Job {

    private final Logger logger = LoggerFactory.getLogger(BackgroundJob.class);

    @Override
    public void doRun() throws JobInterruptException {

        // pull object from ServletContext, which was added in the apllication's run method
        GpioPinDigitalOutput myLed  = (GpioPinDigitalOutput) SundialJobScheduler.getServletContext().getAttribute("led");

        logger.info("BackgroundJob is led on true/false ?: " + myLed.isHigh());
    }
}