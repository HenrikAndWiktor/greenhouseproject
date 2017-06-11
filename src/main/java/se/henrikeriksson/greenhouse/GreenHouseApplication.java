package se.henrikeriksson.greenhouse;

import com.pi4j.io.gpio.*;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import static se.henrikeriksson.greenhouse.resources.GpioPins.getPinFromBOARD;

import org.knowm.dropwizard.sundial.SundialBundle;
import org.knowm.dropwizard.sundial.SundialConfiguration;
import se.henrikeriksson.greenhouse.health.Health;
import se.henrikeriksson.greenhouse.resources.GreenHouseStatus;

public class GreenHouseApplication extends Application<GreenHouseConfiguration> {

    public static void main(final String[] args) throws Exception {
        new GreenHouseApplication().run(args);
    }

    @Override
    public String getName() {
        return "GreenHouse";
    }
    GpioController gpio = null;
    GpioPinDigitalOutput myLed = null;

    @Override
    public void initialize(final Bootstrap<GreenHouseConfiguration> bootstrap) {
        // TODO: application initialization
        // create gpio controller instance
        gpio = GpioFactory.getInstance();
        myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,   // PIN
                "My LED",           // PIN FRIENDLY NAME (optional)
                PinState.LOW);      // PIN STARTUP STATE (optional)
        myLed.setShutdownOptions(true, PinState.LOW);

        bootstrap.addBundle(new SundialBundle<GreenHouseConfiguration>() {

            @Override
            public SundialConfiguration getSundialConfiguration(GreenHouseConfiguration configuration) {
                return configuration.getSundialConfiguration();
            }
        });
    }


    @Override
    public void run(final GreenHouseConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.healthChecks().register("myHealthCheck", new Health());
        final GreenHouseStatus statusResource = new GreenHouseStatus(configuration, myLed);
        environment.jersey().register(statusResource);

        // Add object to ServletContext for accessing from Sundial Jobs
        environment.getApplicationContext().setAttribute("led", myLed);
    }




}
