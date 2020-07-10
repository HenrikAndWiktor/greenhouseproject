package se.henrikeriksson.greenhouse.jobs;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.knowm.sundial.Job;
import org.knowm.sundial.SundialJobScheduler;
import org.knowm.sundial.annotations.CronTrigger;
import org.knowm.sundial.exceptions.JobInterruptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.henrikeriksson.greenhouse.utils.Radio433Utility;

import java.io.IOException;

// Start every minute from 18:00 to 18:04
@CronTrigger(cron = "0 0,1,2,3,4 18 * * ?")
public class WaterStart extends Job {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public void doRun() throws JobInterruptException {

        //Radio433Utility.startWater();

        GpioPinDigitalOutput wateringPin = (GpioPinDigitalOutput) SundialJobScheduler.getServletContext()
                .getAttribute("watering");

        wateringPin.high();

        }
}
