package com.assignment.spring.infrastructure.api;

import com.assignment.spring.infrastructure.api.response.WeatherResponse;
import com.assignment.spring.infrastructure.TimeProvider;
import com.assignment.spring.domain.WeatherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherMapService {

    @Autowired
    private OpenWeatherMapRest openWeatherMapRest;

    @Autowired
    private TimeProvider timeProvider;

    public WeatherEntity getWeatherFromOpenWeatherMap(String city) {
        WeatherResponse response = openWeatherMapRest.getWeather(city);
        return WeatherEntity.builder()
                .city(response.getName())
                .country(response.getSys().getCountry())
                .temperature(response.getMain().getTemp())
                .updatedOn(timeProvider.getNow()).build();
    }
}
