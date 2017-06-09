package se.henrikeriksson.greenhouse.api;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by henrikeriksson on 2017-06-04.
 */


public class GreenHouseTemp {

    private Double current;

    public GreenHouseTemp() {

    }

    public GreenHouseTemp(Double current) {
        this.current = current;
    }


    @JsonProperty
    public Double getCurrent() {
        return current;
    }
}
