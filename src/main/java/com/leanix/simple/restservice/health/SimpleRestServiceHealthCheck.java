package com.leanix.simple.restservice.health;

import com.codahale.metrics.health.HealthCheck;

public class SimpleRestServiceHealthCheck extends HealthCheck {
    private final String content;

    public SimpleRestServiceHealthCheck(String content) {
        this.content = content;
    }

    @Override
    protected Result check() throws Exception {
        final String test = String.format(content, "TEST");
        if (!test.contains("TEST")) {
            return Result.unhealthy("content doesn't include a name");
        }
        return Result.healthy();
    }
}
