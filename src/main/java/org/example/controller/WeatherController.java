package org.example.controller;

import ch.qos.logback.core.model.Model;
import org.example.exception.WeatherServiceException;
import org.example.model.WeatherDTO;
import org.example.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public ResponseEntity<?> getWeatherGet(@RequestParam String city) throws WeatherServiceException {
        WeatherDTO weather = weatherService.getWeather(city);
        return ResponseEntity.ok(weather);
    }

    @PostMapping(consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<?> getWeatherPost(@RequestParam String city) throws WeatherServiceException {
        WeatherDTO weather = weatherService.getWeather(city);
        return ResponseEntity.ok(weather);
    }
}