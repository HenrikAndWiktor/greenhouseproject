package se.henrikeriksson.greenhouse.jobs;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.knowm.sundial.Job;
import org.knowm.sundial.SundialJobScheduler;
import org.knowm.sundial.annotations.CronTrigger;
import org.knowm.sundial.exceptions.JobInterruptException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import se.henrikeriksson.greenhouse.resources.GreenHouseStatus;

import java.time.LocalTime;

/**
 * Created by henrikeriksson on 2017-06-12.
 */
@CronTrigger(cron = "0 * * * * ?")
public class SaveMoistureTime extends Job {
    public static final Logger log = LoggerFactory.getLogger(SaveMoistureTime.class);

    @Override
    public void doRun() throws JobInterruptException {
        GreenHouseStatus.lastTimeOnMoisture = new GreenHouseStatus.Time(LocalTime.now().getHour()+2,
                LocalTime.now().getMinute());
        if (((GpioPinDigitalOutput) SundialJobScheduler.getServletContext().getAttribute("led")).isLow()) {
            log.info(String.format("SaveMoistureTime: Last time on moisture: %s GMT+1", GreenHouseStatus.lastTimeOnMoisture.toString()));
        }
    }
}
