package com.weather.backend.domain.model;

import java.time.Instant;
import java.util.Objects;

public class WeatherObservation {
    private final Long id;
    private final String city;
    private final double temperatureC;
    private final int humidity;
    private final Instant observedAt;

    public WeatherObservation(Long id, String city, double temperatureC, int humidity, Instant observedAt) {
        this.id = id;
        this.city = Objects.requireNonNull(city);
        this.temperatureC = temperatureC;
        this.humidity = humidity;
        this.observedAt = Objects.requireNonNull(observedAt);
    }

    public Long getId() { return id; }
    public String getCity() { return city; }
    public double getTemperatureC() { return temperatureC; }
    public int getHumidity() { return humidity; }
    public Instant getObservedAt() { return observedAt; }
}
