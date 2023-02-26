package com.assignment.spring.weather;

public class WeatherMapper {

    public static WeatherDTO getDTOFromEntity(WeatherEntity weatherEntity) {
        WeatherDTO dto = new WeatherDTO();
        dto.id = weatherEntity.getId();
        dto.city = weatherEntity.getCity();
        dto.country = weatherEntity.getCountry();
        dto.temperature = weatherEntity.getTemperature();
        return dto;
    }

    public static WeatherEntity getEntityFromDTO(WeatherDTO dto) {
        WeatherEntity entity = new WeatherEntity();
        entity.setId(dto.id);
        entity.setCity(dto.city);
        entity.setCountry(dto.country);
        entity.setTemperature(dto.temperature);
        return entity;
    }
}
