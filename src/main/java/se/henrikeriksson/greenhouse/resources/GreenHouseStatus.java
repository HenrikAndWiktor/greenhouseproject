package se.henrikeriksson.greenhouse.resources;

import com.codahale.metrics.annotation.Timed;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.henrikeriksson.greenhouse.GreenHouseConfiguration;

import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.FileNotFoundException;

import se.henrikeriksson.greenhouse.api.GreenHouseTemp;
import se.henrikeriksson.greenhouse.api.PinState;


@Produces(MediaType.APPLICATION_JSON)
@Path("/greenhousestatus")
public class GreenHouseStatus {

    Logger log = LoggerFactory.getLogger(GreenHouseStatus.class);
    GpioPinDigitalOutput myLed = null;
    GreenHouseConfiguration configuration = null;

    public GreenHouseStatus(GreenHouseConfiguration configuration, GpioPinDigitalOutput myLed){
        this.configuration = configuration;
        this.myLed = myLed;
    }

    @GET
    @Timed
    @Path("/temp")
    public GreenHouseTemp getGreenHouseTemp(){
        try {

            java.util.Scanner scan = new java.util.Scanner(new java.io.File(configuration.getTempsensorfile()));
            scan.nextLine();
            String temp = scan.nextLine().split("=")[1];
            temp = new StringBuilder(temp).insert(temp.length()-3, ".").toString();
            Double tempAsDouble = Double.parseDouble(temp);
            return new GreenHouseTemp(tempAsDouble);
        } catch (FileNotFoundException fnfe) {
            log.error("Couldn't read temperature file: "+configuration.getTempsensorfile());
        }
        return null;
    }
    @POST
    @Timed @Path("/pin") public PinState updatePin(@QueryParam("state") String state) {
        switch (state) {
            case "on":
                myLed.high();
                break;
            case "off":
                myLed.low();
                break;
            case "toggle":
                myLed.toggle();
        }

        return new PinState(myLed.isHigh());
    }
}
