package se.henrikeriksson.greenhouse.resources;

import com.codahale.metrics.annotation.Timed;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.henrikeriksson.greenhouse.GreenHouseConfiguration;
import se.henrikeriksson.greenhouse.api.GreenHouseInfo;
import se.henrikeriksson.greenhouse.api.PinState;
import se.henrikeriksson.greenhouse.utils.TempFileHelper;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Path("/greenhousestatus")
public class GreenHouseStatus {
    private final Logger log = LoggerFactory.getLogger(GreenHouseStatus.class);

    public static class Time {
        private int h;
        private int m;

        public Time(int hour, int min) {
            h=hour;
            m=min;
        }

        public int getHour() {
            return h;
        }

        public int getMinute() {
            return m;
        }

        @Override
        public String toString() {
            return getHour()+":"+getMinute();
        }
    }
    GpioPinDigitalOutput wateringPin, acPin = null;
    GpioPinDigitalInput moisturePin, waterTankPin = null;
    GreenHouseConfiguration configuration = null;
    Client wiktorPiClient = null;
    public static Time lastTimeOnMoisture;

    public GreenHouseStatus(GreenHouseConfiguration configuration, GpioPinDigitalOutput wateringPin, GpioPinDigitalOutput acPin, GpioPinDigitalInput moisturePin, GpioPinDigitalInput waterTankPin, Client wiktorPiClient){
        this.configuration = configuration;
        this.wateringPin = wateringPin;
        this.moisturePin = moisturePin;
        this.wiktorPiClient = wiktorPiClient;
        this.waterTankPin = waterTankPin;
        this.acPin = acPin;
    }

    @GET
    @Timed
    @Path("/info")
    public GreenHouseInfo getGreenHouseInfo(){

        Double indoorTemp = TempFileHelper.getTemperatureFromFile(configuration.getInDoorTempsensorfile());
        Double outdoorTemp = TempFileHelper.getTemperatureFromFile(configuration.getOutDoorTempsensorfile());
        return new GreenHouseInfo(indoorTemp, moisturePin.isHigh(), outdoorTemp, new PinState(wateringPin.isHigh()), new PinState(acPin.isHigh()), new PinState(waterTankPin.isHigh()));
        //return new GreenHouseInfo(tempAsDouble, moisturePin.isHigh(), getOutdoorTemperature(), new PinState(Radio433Utility.isWaterOn), new PinState(acPin.isHigh()), new PinState(waterTankPin.isHigh()));
    }

    private void stateOfPin(GpioPinDigitalOutput gpdo, String state) {
        switch (state) {
            case "on":
                gpdo.high();
                break;
            case "off":
                gpdo.low();
                break;
            case "toggle":
                gpdo.toggle();
        }
    }

    @POST
    @Timed
    @Path("/pin")
    public PinState updatePin(@QueryParam("state") String state) {
        stateOfPin(wateringPin, state);
        /**switch (state) {
         case "on":
         Radio433Utility.startWater();
         break;
         case "off":
         Radio433Utility.stopWater();
         break;
         case "toggle":
         if (Radio433Utility.isWaterOn)
         Radio433Utility.stopWater();
         else
         Radio433Utility.startWater();
         break;
         }
         return new PinState(Radio433Utility.isWaterOn);
         **/
        return new PinState(wateringPin.isHigh());
    }

    @POST @Timed @Path("/fan")
    public PinState updateAirCondition(@QueryParam("state") String state) {
        stateOfPin(acPin, state);
        return new PinState(acPin.isHigh());
    }
}
