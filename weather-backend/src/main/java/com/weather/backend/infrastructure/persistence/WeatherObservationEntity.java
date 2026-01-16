package com.weather.backend.infrastructure.persistence;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "weather_observations")
public class WeatherObservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private double temperatureC;

    @Column(nullable = false)
    private int humidity;

    @Column(nullable = false)
    private Instant observedAt;

    protected WeatherObservationEntity() { }

    public WeatherObservationEntity(String city, double temperatureC, int humidity, Instant observedAt) {
        this.city = city;
        this.temperatureC = temperatureC;
        this.humidity = humidity;
        this.observedAt = observedAt;
    }

    public Long getId() { return id; }
    public String getCity() { return city; }
    public double getTemperatureC() { return temperatureC; }
    public int getHumidity() { return humidity; }
    public Instant getObservedAt() { return observedAt; }
}
