package com.weather.backend.api.dto;

import java.time.Instant;
import java.util.List;

public record ForecastResponse(
        String city,
        double latitude,
        double longitude,
        String timezone,
        Instant fetchedAt,
        List<HourlyPoint> hourly
) {
    public record HourlyPoint(
            String time,          // ISO string from provider (timezone-aware)
            double temperatureC,
            Integer humidity
    ) {}
}
