package se.henrikeriksson.greenhouse.jobs;

import com.pi4j.io.gpio.PinState;
import org.knowm.sundial.Job;
import org.knowm.sundial.annotations.CronTrigger;
import org.knowm.sundial.exceptions.JobInterruptException;
import se.henrikeriksson.greenhouse.GreenHouseApplication;
import se.henrikeriksson.greenhouse.utils.MailHelper;

@CronTrigger(cron = "0 * * * * ?")
public class MailJob extends Job {

    @Override
    public void doRun() throws JobInterruptException {
        if (GreenHouseApplication.instance.waterTankPin.isState(PinState.HIGH)) {
            MailHelper.waterhighmail();
        }
        if (GreenHouseApplication.instance.waterTankPin.isState(PinState.LOW)) {
            MailHelper.waterlowmail();
        }
    }
}
