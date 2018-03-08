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

    public static void main(final String[] args) throws Exception {
        new GreenHouseApplication().run(args);
    }

    @Override
    public String getName() {
        return "GreenHouse";
    }

    private GpioController gpio = null;
    private GpioPinDigitalOutput wateringPin, acPin = null;
    private GpioPinDigitalInput moisturePin = null, waterTankPin = null;

    @Override
    public void initialize(final Bootstrap<GreenHouseConfiguration> bootstrap) {
        // create gpio controller instance
        gpio = GpioFactory.getInstance();
        acPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "My fan", PinState.LOW);
        acPin.setShutdownOptions(true, PinState.LOW);
        wateringPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,   // PIN
                "My LED",           // PIN FRIENDLY NAME (optional)
                PinState.LOW);      // PIN STARTUP STATE (optional)
        wateringPin.setShutdownOptions(true, PinState.LOW);

        moisturePin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00, "My moisture sensor", PinPullResistance.PULL_DOWN);
        // set shutdown state for this input pin
        moisturePin.setShutdownOptions(true);

        waterTankPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_27, "My water tank level indicator", PinPullResistance.PULL_UP);
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
        environment.healthChecks().register("myHealthCheck", new Health());
        environment.healthChecks().register("remoteHealth", new RemoteHealth());
        final Client client = new JerseyClientBuilder(environment).build("DemoRESTClient");

        final GreenHouseStatus statusResource = new GreenHouseStatus(configuration, wateringPin, acPin, moisturePin, waterTankPin, client);
        environment.jersey().register(statusResource);

        // Add object to ServletContext for accessing from Sundial Jobs
        environment.getApplicationContext().setAttribute("led", wateringPin);
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter("allowedOrigins", "*");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

    }




}
