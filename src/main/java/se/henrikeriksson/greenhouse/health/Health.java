package se.henrikeriksson.greenhouse.health;

public class Health extends com.codahale.metrics.health.HealthCheck {
    public Health(){}
    protected Result check() throws java.lang.Exception {
        return Result.healthy("Application is running");
    }
}
