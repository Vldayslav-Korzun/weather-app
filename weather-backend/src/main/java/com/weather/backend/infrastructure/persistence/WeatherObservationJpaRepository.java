package com.weather.backend.infrastructure.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherObservationJpaRepository extends JpaRepository<WeatherObservationEntity, Long> {
    Page<WeatherObservationEntity> findByCityIgnoreCase(String city, Pageable pageable);
}
