package com.assignment.spring.weather;

import com.assignment.spring.weather.WeatherDTO.WeatherDTOBuilder;
import org.springframework.stereotype.Service;

@Service
public class WeatherMapper {

    public WeatherDTO getDTOFromEntity(WeatherEntity weatherEntity) {
        return new WeatherDTOBuilder()
                .id(weatherEntity.getId())
                .city(weatherEntity.getCity())
                .country(weatherEntity.getCountry())
                .temperature(weatherEntity.getTemperature()).build();
    }

    public WeatherEntity getEntityFromDTO(WeatherDTO dto) {
        return WeatherEntity.builder()
                .id(dto.getId())
                .city(dto.getCity())
                .country(dto.getCountry())
                .temperature(dto.getTemperature()).build();
    }
}
