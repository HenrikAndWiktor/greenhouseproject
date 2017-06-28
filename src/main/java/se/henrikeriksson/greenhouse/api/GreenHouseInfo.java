package se.henrikeriksson.greenhouse.api;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by henrikeriksson on 2017-06-04.
 */


public class GreenHouseInfo {

    private Double greenhousetemperature;
    private boolean soilmoisture;
    private Double outdoortemperature;
    private PinState waterflow, fan;

    public GreenHouseInfo() {

    }

    public GreenHouseInfo(Double greenhousetemperature, boolean isSoilMoisture, Double outdoortemperature, PinState waterflow, PinState fan) {
        this.greenhousetemperature = greenhousetemperature;
        this.soilmoisture = !isSoilMoisture;
        this.outdoortemperature = outdoortemperature;
        this.waterflow = waterflow;
        this.fan = fan;
    }


    @JsonProperty
    public Double getGreenhousetemperature() {
        return greenhousetemperature;
    }

    @JsonProperty
    public String getSoilmoisture() {
        return soilmoisture ? "wet" : "dry";
    }

    @JsonProperty
    public Double getOutdoortemperature() {
        return outdoortemperature;
    }

    @JsonProperty
    public String getWaterflow() {return waterflow.state();}

    @JsonProperty public String getFanon() {
        return fan.state();
    }

}
