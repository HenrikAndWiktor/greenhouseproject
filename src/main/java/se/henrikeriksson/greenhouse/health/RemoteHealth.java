package se.henrikeriksson.greenhouse.health;

import com.codahale.metrics.health.HealthCheck;
import se.henrikeriksson.greenhouse.api.URLHelper;

public class RemoteHealth extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return URLHelper.pingGET("http://wiktoreriksson.se/subdomain/weather/tempapp.json",5) ?
                Result.healthy("Wiktors PI accessible"):
                Result.unhealthy("Wiktors PI down");
    }
}
