package se.henrikeriksson.greenhouse.health;

import com.codahale.metrics.health.HealthCheck;
import se.henrikeriksson.greenhouse.api.URLHelper;

public class RemoteHealth extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return URLHelper.ping("wiktoreriksson.se",1) ?
                Result.healthy("Wiktors PI accessible"):
                Result.unhealthy("Wiktors PI down");
    }
}
