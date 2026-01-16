package com.weather.backend.application.dto;

import java.time.Instant;

public class ObservationResponse {
    public Long id;
    public String city;
    public double temperatureC;
    public int humidity;
    public Instant observedAt;

    public ObservationResponse(Long id, String city, double temperatureC, int humidity, Instant observedAt) {
        this.id = id;
        this.city = city;
        this.temperatureC = temperatureC;
        this.humidity = humidity;
        this.observedAt = observedAt;
    }
}
