package com.assignment.spring.infrastructure.weather;

import com.assignment.spring.domain.WeatherEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WeatherRepository extends CrudRepository<WeatherEntity, Integer> {
    Optional<WeatherEntity> findByCityIgnoreCase(String city);

}
