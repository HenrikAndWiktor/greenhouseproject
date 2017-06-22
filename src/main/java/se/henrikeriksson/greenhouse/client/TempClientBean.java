package se.henrikeriksson.greenhouse.client;

/**
 * Created by henrikeriksson on 2017-06-17.
 */
public class TempClientBean {
    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String temperature;
    public String max;
    public String min;


    public TempClientBean() {

    }
    public TempClientBean(String temperature,String max, String min) {
        this.temperature = temperature;
        this.max = max;
        this.min = min;

    }
}
