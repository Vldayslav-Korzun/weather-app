package com.weather.backend.api.controller;

import com.weather.backend.api.dto.ForecastResponse;
import com.weather.backend.infrastructure.external.OpenMeteoClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ForecastController {

    private final OpenMeteoClient client;

    public ForecastController(OpenMeteoClient client) {
        this.client = client;
    }

    @GetMapping("/forecast")
    public ResponseEntity<ForecastResponse> forecast(@RequestParam String city) {
        return ResponseEntity.ok(client.forecastByCity(city));
    }
}
