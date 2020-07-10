package se.henrikeriksson.greenhouse.jobs;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.knowm.sundial.Job;
import org.knowm.sundial.SundialJobScheduler;
import org.knowm.sundial.annotations.CronTrigger;
import org.knowm.sundial.exceptions.JobInterruptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.henrikeriksson.greenhouse.utils.Radio433Utility;

import java.io.IOException;

// Stop every hour at minute 14,29,44,59
@CronTrigger(cron = "0 14,29,44,59 * * * ?")
public class WaterShut extends Job {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public void doRun() throws JobInterruptException {
        //Radio433Utility.stopWater();

        GpioPinDigitalOutput wateringPin = (GpioPinDigitalOutput) SundialJobScheduler.getServletContext()
                .getAttribute("watering");

        wateringPin.low();
    }
}
