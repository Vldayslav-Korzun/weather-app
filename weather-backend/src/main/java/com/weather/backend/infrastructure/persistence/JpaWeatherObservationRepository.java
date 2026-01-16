package com.weather.backend.infrastructure.persistence;

import com.weather.backend.domain.model.WeatherObservation;
import com.weather.backend.domain.repository.WeatherObservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class JpaWeatherObservationRepository implements WeatherObservationRepository {

    private final WeatherObservationJpaRepository jpa;

    public JpaWeatherObservationRepository(WeatherObservationJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public WeatherObservation save(WeatherObservation observation) {
        var saved = jpa.save(WeatherObservationMapper.toEntity(observation));
        return WeatherObservationMapper.toDomain(saved);
    }

    @Override
    public Page<WeatherObservation> findAll(Pageable pageable) {
        return jpa.findAll(pageable).map(WeatherObservationMapper::toDomain);
    }

    @Override
    public Page<WeatherObservation> findByCity(String city, Pageable pageable) {
        return jpa.findByCityIgnoreCase(city, pageable).map(WeatherObservationMapper::toDomain);
    }
}
