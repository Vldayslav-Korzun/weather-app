package com.weather.backend.api.controller;

import com.weather.backend.application.dto.CreateObservationRequest;
import com.weather.backend.application.dto.ObservationResponse;
import com.weather.backend.application.dto.PagedResponse;
import com.weather.backend.application.service.WeatherObservationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/observations")
public class WeatherObservationController {

    private final WeatherObservationService service;

    public WeatherObservationController(WeatherObservationService service) {
        this.service = service;
    }

    @PostMapping
    public ObservationResponse create(@Valid @RequestBody CreateObservationRequest request) {
        return service.create(request);
    }

    @GetMapping
    public PagedResponse<ObservationResponse> list(
            @RequestParam Optional<String> city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return service.list(city, page, size);
    }
}
