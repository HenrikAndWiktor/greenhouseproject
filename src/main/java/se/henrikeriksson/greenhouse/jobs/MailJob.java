package se.henrikeriksson.greenhouse.jobs;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import org.knowm.sundial.Job;
import org.knowm.sundial.SundialJobScheduler;
import org.knowm.sundial.annotations.CronTrigger;
import org.knowm.sundial.exceptions.JobInterruptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.henrikeriksson.greenhouse.utils.MailHelper;

@CronTrigger(cron = "0 * * * * ?")
public class MailJob extends Job {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void doRun() throws JobInterruptException {
        if (((GpioPinDigitalInput) SundialJobScheduler.getServletContext().getAttribute("waterTank")).isLow()) {
            log.info("Water tank pin state is low");
            MailHelper.waterlowmail();
        }else {
            log.info("Water tank pin state is high");
            MailHelper.waterhighmail();
        }

    }
}
