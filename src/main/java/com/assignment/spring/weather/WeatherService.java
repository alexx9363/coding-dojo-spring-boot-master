package com.assignment.spring.weather;

import com.assignment.spring.api.OpenWeatherMapRest;
import com.assignment.spring.api.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    OpenWeatherMapRest openWeatherMapRest;

    @Autowired
    WeatherRepository weatherRepository;

    public WeatherEntity getWeather(String city) {
        WeatherResponse response = openWeatherMapRest.getWeather(city);

        WeatherEntity entity = weatherRepository.findByCityIgnoreCase(city).orElseGet(WeatherEntity::new);
        entity.setCity(response.getName());
        entity.setCountry(response.getSys().getCountry());
        entity.setTemperature(response.getMain().getTemp());
        return weatherRepository.save(entity);
    }
}
