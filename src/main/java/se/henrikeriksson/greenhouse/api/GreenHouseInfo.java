package se.henrikeriksson.greenhouse.api;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by henrikeriksson on 2017-06-04.
 */


public class GreenHouseInfo {

    private Double greenhousetemperature;
    private boolean soilmoisture;
    private Double outdoortemperature;
    private PinState waterflow, fan, waterTank;

    public GreenHouseInfo() {

    }

    public GreenHouseInfo(Double greenhousetemperature, boolean isSoilMoisture, Double outdoortemperature, PinState waterflow, PinState fan, PinState waterTank) {
        this.greenhousetemperature = greenhousetemperature;
        this.soilmoisture = !isSoilMoisture;
        this.outdoortemperature = outdoortemperature;
        this.waterflow = waterflow;
        this.fan = fan;
        this.waterTank = waterTank;
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

    @JsonProperty
    public String getWatertanklevel() {return waterTank.state().equals("on") ? "high" : "low";}

    @JsonProperty public String getFanstate() {
        return fan.state();
    }

}
