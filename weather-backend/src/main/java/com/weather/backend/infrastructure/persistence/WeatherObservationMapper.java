package com.weather.backend.infrastructure.persistence;

import com.weather.backend.domain.model.WeatherObservation;

public final class WeatherObservationMapper {

    private WeatherObservationMapper() {}

    public static WeatherObservationEntity toEntity(WeatherObservation d) {
        return new WeatherObservationEntity(
                d.getCity(),
                d.getTemperatureC(),
                d.getHumidity(),
                d.getObservedAt()
        );
    }

    public static WeatherObservation toDomain(WeatherObservationEntity e) {
        return new WeatherObservation(
                e.getId(),
                e.getCity(),
                e.getTemperatureC(),
                e.getHumidity(),
                e.getObservedAt()
        );
    }
}
