package com.weather.backend.application.service;

import com.weather.backend.application.dto.CreateObservationRequest;
import com.weather.backend.application.dto.ObservationResponse;
import com.weather.backend.application.dto.PagedResponse;
import com.weather.backend.domain.model.WeatherObservation;
import com.weather.backend.domain.repository.WeatherObservationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class WeatherObservationService {

    private final WeatherObservationRepository repo;

    public WeatherObservationService(WeatherObservationRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public ObservationResponse create(CreateObservationRequest req) {
        var domain = new WeatherObservation(
                null,
                req.city,
                req.temperatureC,
                req.humidity,
                req.observedAt
        );

        var saved = repo.save(domain);

        return new ObservationResponse(
                saved.getId(),
                saved.getCity(),
                saved.getTemperatureC(),
                saved.getHumidity(),
                saved.getObservedAt()
        );
    }

    @Transactional(readOnly = true)
    public PagedResponse<ObservationResponse> list(Optional<String> city, int page, int size) {
        var pageable = PageRequest.of(page, size);

        var resultPage = city
                .filter(s -> !s.isBlank())
                .map(c -> repo.findByCity(c, pageable))
                .orElseGet(() -> repo.findAll(pageable));

        var items = resultPage.getContent().stream()
                .map(d -> new ObservationResponse(
                        d.getId(),
                        d.getCity(),
                        d.getTemperatureC(),
                        d.getHumidity(),
                        d.getObservedAt()
                ))
                .toList();

        return new PagedResponse<>(
                items,
                resultPage.getNumber(),
                resultPage.getSize(),
                resultPage.getTotalElements(),
                resultPage.getTotalPages()
        );
    }
}
