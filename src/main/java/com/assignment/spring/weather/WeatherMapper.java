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
        WeatherEntity entity = new WeatherEntity();
        entity.setId(dto.getId());
        entity.setCity(dto.getCity());
        entity.setCountry(dto.getCountry());
        entity.setTemperature(dto.getTemperature());
        return entity;
    }
}
