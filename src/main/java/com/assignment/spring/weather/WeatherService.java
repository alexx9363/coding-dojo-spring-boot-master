package com.assignment.spring.weather;

import com.assignment.spring.api.OpenWeatherMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherService {
    @Autowired
    private OpenWeatherMapService openWeatherMapService;
    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private TimeProvider timeProvider;

    private static final int WEATHER_MINUTES_IS_UP_TO_DATE = 10;

    public WeatherEntity getWeather(String city) {
        return weatherRepository.findByCityIgnoreCase(city).filter(this::isUpToDate).orElseGet(() -> updateWeather(city));
    }

    private boolean isUpToDate(WeatherEntity weather) {
        return timeProvider.getNow().isBefore(weather.getUpdatedOn().plusMinutes(WEATHER_MINUTES_IS_UP_TO_DATE));
    }

    private WeatherEntity updateWeather(String city) {
        return weatherRepository.save(openWeatherMapService.getWeatherFromOpenWeatherMap(city));
    }
}
