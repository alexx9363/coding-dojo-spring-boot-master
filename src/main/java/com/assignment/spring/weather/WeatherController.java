package com.assignment.spring.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherMapper weatherMapper;

    @GetMapping
    public ResponseEntity<WeatherDTO> getWeather(@RequestParam String city) {
        return ResponseEntity.ok(weatherMapper.getDTOFromEntity(weatherService.getWeather(city)));
    }
}
