package com.assignment.spring.api;

import com.assignment.spring.api.response.WeatherResponse;
import com.assignment.spring.weather.TimeProvider;
import com.assignment.spring.weather.WeatherEntity;
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
