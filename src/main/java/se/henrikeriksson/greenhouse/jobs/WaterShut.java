package se.henrikeriksson.greenhouse.jobs;

import org.knowm.sundial.Job;
import org.knowm.sundial.annotations.CronTrigger;
import org.knowm.sundial.exceptions.JobInterruptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.henrikeriksson.greenhouse.utils.Radio433Utility;

import java.io.IOException;

// 0 0/5 13 * * ? execute stop every 5 min during 1 hour from  17.
@CronTrigger(cron = "0 0/5 15 * * ?")
public class WaterShut extends Job {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public void doRun() throws JobInterruptException {
        Radio433Utility.stopWater();
    }
}
