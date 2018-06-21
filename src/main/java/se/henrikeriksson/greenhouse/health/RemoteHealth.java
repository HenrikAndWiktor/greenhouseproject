package se.henrikeriksson.greenhouse.health;

import com.codahale.metrics.health.HealthCheck;
import se.henrikeriksson.greenhouse.utils.URLHelper;

public class RemoteHealth extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return URLHelper.ping("192.168.0.103",1) ?
                Result.healthy("Wiktors PI accessible"):
                Result.unhealthy("Wiktors PI down");
    }
}
