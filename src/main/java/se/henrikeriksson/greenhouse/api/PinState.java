package se.henrikeriksson.greenhouse.api;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by henrikeriksson on 2017-06-04.
 */


public class PinState {

    private boolean isPinOn;

    public PinState() {

    }

    public PinState(boolean isPinOn) {
        this.isPinOn = isPinOn;
    }


    @JsonProperty
    public  boolean isPinOn() {
        return isPinOn;
    }
}
