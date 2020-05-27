package se.henrikeriksson.greenhouse.resources;

import com.codahale.metrics.annotation.Timed;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.henrikeriksson.greenhouse.GreenHouseConfiguration;

import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;

import se.henrikeriksson.greenhouse.api.GreenHouseInfo;
import se.henrikeriksson.greenhouse.api.PinState;
import se.henrikeriksson.greenhouse.client.TempClientBean;
import se.henrikeriksson.greenhouse.utils.Radio433Utility;


@Produces(MediaType.APPLICATION_JSON)
@Path("/greenhousestatus")
public class GreenHouseStatus {
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
    private final Logger log = LoggerFactory.getLogger(GreenHouseStatus.class);
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

    public Double getOutdoorTemperature()
    {
        TempClientBean tempClientBean = null;
        try {
            //Do not hard code in your application
            WebTarget webTarget = wiktorPiClient.target("http://www.wiktoreriksson.se/subdomain/weather/tempapp.json");
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();
            tempClientBean = response.readEntity(TempClientBean.class);

            return Double.parseDouble(tempClientBean.getTemperature());
        } catch(NumberFormatException nfe)
            {
                log.error("Error when calling Wiktors PI for outdoor temp. Value is not a double: "+tempClientBean.getTemperature());
                return null;

        } catch (Exception e) {
            log.error("Error when calling Wiktors PI for outdoor temp: "+e.getMessage());
            return null;
        }

    }


    @GET
    @Timed
    @Path("/test")
    public String test(){
        return "hej";
    }

    @GET
    @Timed
    @Path("/info")
    public GreenHouseInfo getGreenHouseInfo(){
        try {

            java.util.Scanner scan = new java.util.Scanner(new java.io.File(configuration.getTempsensorfile()));
            scan.nextLine();
            String temp = scan.nextLine().split("=")[1];
            temp = new StringBuilder(temp).insert(temp.length()-3, ".").toString();
            Double tempAsDouble = Double.parseDouble(temp);
            return new GreenHouseInfo(tempAsDouble, moisturePin.isHigh(), getOutdoorTemperature(), new PinState(wateringPin.isHigh()), new PinState(acPin.isHigh()), new PinState(waterTankPin.isHigh()));
            //return new GreenHouseInfo(tempAsDouble, moisturePin.isHigh(), getOutdoorTemperature(), new PinState(Radio433Utility.isWaterOn), new PinState(acPin.isHigh()), new PinState(waterTankPin.isHigh()));

        } catch (FileNotFoundException fnfe) {
            log.error("Couldn't read temperature file: "+configuration.getTempsensorfile());
        }
        return null;
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
