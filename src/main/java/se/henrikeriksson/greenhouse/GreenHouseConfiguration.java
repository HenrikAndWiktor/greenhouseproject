package se.henrikeriksson.greenhouse;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.client.JerseyClientConfiguration;
import org.hibernate.validator.constraints.*;
import org.knowm.dropwizard.sundial.SundialConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class GreenHouseConfiguration extends Configuration {
    @NotEmpty
    private String inDoorTempsensorfile;

    @JsonProperty
    public String getInDoorTempsensorfile() {
        return inDoorTempsensorfile;
    }

    @NotEmpty
    private String outDoorTempsensorfile;

    @JsonProperty
    public String getOutDoorTempsensorfile() {
        return outDoorTempsensorfile;
    }

    @NotEmpty
    private String mailPassword;

    @JsonProperty
    public String getMailPassword() {
        return mailPassword;
    }

    @NotEmpty
    private String mailUser;

    @JsonProperty
    public String getMailUser() {
        return mailUser;
    }

    @JsonProperty
    public void setInDoorTempsensorfile(String inDoorTempsensorfile) {
        this.inDoorTempsensorfile = inDoorTempsensorfile;
    }

    @JsonProperty
    public void setOutDoorTempsensorfile(String outDoorTempsensorfile) {
        this.outDoorTempsensorfile = outDoorTempsensorfile;
    }

    @Valid
    @NotNull
    public SundialConfiguration sundialConfiguration = new SundialConfiguration();

    @JsonProperty("sundial")
    public SundialConfiguration getSundialConfiguration() {

        return sundialConfiguration;
    }

    @Valid
    @NotNull
    @JsonProperty
    private JerseyClientConfiguration httpClient = new JerseyClientConfiguration();

    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return httpClient;
    }
}
