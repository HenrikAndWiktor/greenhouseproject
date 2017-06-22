package se.henrikeriksson.greenhouse.api;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by henrikeriksson on 2017-06-04.
 */


public class PinState {

    private String state;

    public PinState() {

    }

    public PinState(boolean pinstate) {
        state = pinstate ? "on" : "off";
    }


    @JsonProperty
    public  String state() {
        return state;
    }
}
