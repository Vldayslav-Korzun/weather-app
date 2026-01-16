package com.weather.backend.infrastructure.external;

import com.weather.backend.api.dto.ForecastResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpenMeteoClient {

    private final RestClient rest = RestClient.create();

    // 1) Geocoding: find lat/lon by city name
    private static final String GEOCODE_BASE = "https://geocoding-api.open-meteo.com/v1/search";

    // 2) Forecast API
    private static final String FORECAST_BASE = "https://api.open-meteo.com/v1/forecast";

    public ForecastResponse forecastByCity(String city) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("city is required");
        }

        GeoResponse geo = geocode(city.trim());
        if (geo == null || geo.results == null || geo.results.isEmpty()) {
            throw new IllegalArgumentException("City not found: " + city);
        }

        GeoResult top = geo.results.getFirst();

        ForecastApiResponse api = forecast(top.latitude, top.longitude);

        List<ForecastResponse.HourlyPoint> points = mapFirst24Hours(api);

        return new ForecastResponse(
                top.name,
                top.latitude,
                top.longitude,
                api.timezone,
                Instant.now(),
                points
        );
    }

    private GeoResponse geocode(String city) {
        String encoded = UriUtils.encode(city, StandardCharsets.UTF_8);
        String url = GEOCODE_BASE + "?name=" + encoded + "&count=1&language=en&format=json";

        return rest.get()
                .uri(url)
                .retrieve()
                .body(GeoResponse.class);
    }

    private ForecastApiResponse forecast(double lat, double lon) {
        // hourly time + temperature + humidity, 1 day, timezone auto so times match location
        String url = FORECAST_BASE
                + "?latitude=" + lat
                + "&longitude=" + lon
                + "&hourly=temperature_2m,relative_humidity_2m"
                + "&forecast_days=1"
                + "&timezone=auto";

        return rest.get()
                .uri(url)
                .retrieve()
                .body(ForecastApiResponse.class);
    }

    private List<ForecastResponse.HourlyPoint> mapFirst24Hours(ForecastApiResponse api) {
        List<ForecastResponse.HourlyPoint> out = new ArrayList<>();
        if (api == null || api.hourly == null || api.hourly.time == null) return out;

        int n = api.hourly.time.size();
        int limit = Math.min(24, n);

        for (int i = 0; i < limit; i++) {
            String t = api.hourly.time.get(i);
            double temp = safeGet(api.hourly.temperature_2m, i, 0.0);
            Integer hum = safeGetInt(api.hourly.relative_humidity_2m, i);

            out.add(new ForecastResponse.HourlyPoint(t, temp, hum));
        }

        return out;
    }

    private double safeGet(List<Double> list, int i, double def) {
        if (list == null) return def;
        if (i < 0 || i >= list.size()) return def;
        Double v = list.get(i);
        return v == null ? def : v;
    }

    private Integer safeGetInt(List<Integer> list, int i) {
        if (list == null) return null;
        if (i < 0 || i >= list.size()) return null;
        return list.get(i);
    }

    // ====== JSON mappings (minimal) ======

    public static class GeoResponse {
        public List<GeoResult> results;
    }

    public static class GeoResult {
        public String name;
        public double latitude;
        public double longitude;
    }

    public static class ForecastApiResponse {
        public String timezone;
        public Hourly hourly;
    }

    public static class Hourly {
        public List<String> time;
        public List<Double> temperature_2m;
        public List<Integer> relative_humidity_2m;
    }
}
