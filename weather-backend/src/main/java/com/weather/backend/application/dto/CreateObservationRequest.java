package com.weather.backend.application.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public class CreateObservationRequest {
    @NotBlank
    public String city;

    @NotNull
    public Double temperatureC;

    @Min(0) @Max(100)
    public Integer humidity;

    @NotNull
    public Instant observedAt;
}
