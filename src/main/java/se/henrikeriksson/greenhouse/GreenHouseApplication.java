package se.henrikeriksson.greenhouse;

import com.pi4j.io.gpio.*;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import static se.henrikeriksson.greenhouse.resources.GpioPins.getPinFromBOARD;

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

        myLed = gpio.provisionDigitalOutputPin(getPinFromBOARD(12),   // PIN
                "My LED",           // PIN FRIENDLY NAME (optional)
                PinState.LOW);      // PIN STARTUP STATE (optional)
    }

    @Override
    public void run(final GreenHouseConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.healthChecks().register("myHealthCheck", new Health());
        final GreenHouseStatus statusResource = new GreenHouseStatus(configuration, myLed);
        environment.jersey().register(statusResource);
    }
}
