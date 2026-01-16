package com.weather.backend.domain.repository;

import com.weather.backend.domain.model.WeatherObservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WeatherObservationRepository {
    WeatherObservation save(WeatherObservation observation);

    Page<WeatherObservation> findAll(Pageable pageable);

    Page<WeatherObservation> findByCity(String city, Pageable pageable);
}
