package com.weather.backend.api.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MeController {

    @GetMapping("/api/me")
    public Map<String, Object> me(Authentication authentication) {
        Map<String, Object> res = new LinkedHashMap<>();

        res.put("authenticated", authentication != null && authentication.isAuthenticated());
        res.put("name", authentication == null ? null : authentication.getName());
        res.put("authorities", authentication == null
                ? List.of()
                : authentication.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .sorted()
                .toList()
        );

        if (authentication instanceof JwtAuthenticationToken jwtAuth) {
            Jwt jwt = jwtAuth.getToken();
            res.put("issuer", jwt.getIssuer());
            res.put("subject", jwt.getSubject());
            res.put("preferred_username", jwt.getClaimAsString("preferred_username"));
            res.put("email", jwt.getClaimAsString("email"));
            res.put("claims", new TreeSet<>(jwt.getClaims().keySet()));
        }

        return res;
    }
}
