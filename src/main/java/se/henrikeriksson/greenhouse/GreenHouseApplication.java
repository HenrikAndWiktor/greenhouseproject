package se.henrikeriksson.greenhouse;

import com.pi4j.io.gpio.*;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.knowm.dropwizard.sundial.SundialBundle;
import org.knowm.dropwizard.sundial.SundialConfiguration;

import se.henrikeriksson.greenhouse.health.Health;
import se.henrikeriksson.greenhouse.health.RemoteHealth;
import se.henrikeriksson.greenhouse.resources.GreenHouseStatus;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.client.Client;
import java.util.EnumSet;

public class GreenHouseApplication extends Application<GreenHouseConfiguration> {
    public static GreenHouseConfiguration myconfig;
    public static void main(final String[] args) throws Exception {
        new GreenHouseApplication().run(args);
    }

    @Override
    public String getName() {
        return "GreenHouse";
    }

    private GpioController gpio = null;
    private GpioPinDigitalOutput wateringPin, acPin = null;
    private GpioPinDigitalInput moisturePin = null;
    private GpioPinDigitalInput waterTankPin = null;

    @Override
    public void initialize(final Bootstrap<GreenHouseConfiguration> bootstrap) {
        // create gpio controller instance
        gpio = GpioFactory.getInstance();

        // Pi4j/WiringPi GPIO_29 is same as position 40 and named GPIO21 on Pi.
        acPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "My fan", PinState.LOW);
        acPin.setShutdownOptions(true, PinState.LOW);

        // Pi4j/WiringPi GPIO_01 is same as position 12 and named GPIO18 on Pi.
        wateringPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,   // PIN
                "WaterStarter",           // PIN FRIENDLY NAME (optional)
                PinState.LOW);      // PIN STARTUP STATE (optional)
        wateringPin.setShutdownOptions(true, PinState.LOW);

        // Pi4j/WiringPi GPIO_05 is same as position 18 and named GPIO24 on Pi.
        moisturePin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05, "Soil moisture sensor", PinPullResistance.PULL_DOWN);
        // set shutdown state for this input pin
        moisturePin.setShutdownOptions(true);

        // Pi4j/WiringPi GPIO_27 is same as position 36 and named GPIO16 on Pi.
        waterTankPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_27, "Water tank level indicator", PinPullResistance.PULL_UP);
        // set shutdown state for this input pin
        moisturePin.setShutdownOptions(true);

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
        environment.healthChecks().register("HealthCheck", new Health());
        environment.healthChecks().register("RemoteHealth", new RemoteHealth());
        final Client client = new JerseyClientBuilder(environment).build("RESTClient");

        final GreenHouseStatus statusResource = new GreenHouseStatus(configuration, wateringPin, acPin, moisturePin, waterTankPin, client);
        environment.jersey().register(statusResource);

        // Add object to ServletContext for accessing from Sundial Jobs
        environment.getApplicationContext().setAttribute("watering", wateringPin);
        environment.getApplicationContext().setAttribute("waterTank", waterTankPin);
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter("Access-Control-Allow-Origin","*");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        myconfig=configuration;
    }




}
