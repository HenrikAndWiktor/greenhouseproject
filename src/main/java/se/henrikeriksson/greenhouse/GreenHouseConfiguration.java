package se.henrikeriksson.greenhouse;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class GreenHouseConfiguration extends Configuration {
    @NotEmpty
    private String tempsensorfile;

    @JsonProperty
    public String getTempsensorfile() {
        return tempsensorfile;
    }

    @JsonProperty
    public void setTempsensorfile(String tempsensorfile) {
        this.tempsensorfile = tempsensorfile;
    }

}
