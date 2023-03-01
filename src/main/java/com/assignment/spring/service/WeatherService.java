package com.assignment.spring.service;

import com.assignment.spring.domain.WeatherEntity;
import com.assignment.spring.infrastructure.TimeProvider;
import com.assignment.spring.infrastructure.weather.WeatherRepository;
import com.assignment.spring.infrastructure.api.OpenWeatherMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    private OpenWeatherMapService openWeatherMapService;

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private TimeProvider timeProvider;

    private static final int WEATHER_MINUTES_IS_UP_TO_DATE = 10;

    @Value("${weather.update.interval}")
    private int weatherUpdateInterval;

    public WeatherEntity getWeather(String city) {
        return weatherRepository.findByCityIgnoreCase(city).filter(this::isUpToDate).orElseGet(() -> updateWeather(city));
    }

    private boolean isUpToDate(WeatherEntity weather) {
        return timeProvider.getNow().isBefore(weather.getUpdatedOn().plusMinutes(weatherUpdateInterval));
    }

    private WeatherEntity updateWeather(String city) {
        return weatherRepository.save(openWeatherMapService.getWeatherFromOpenWeatherMap(city));
    }
}
