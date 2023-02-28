package com.assignment.spring.weather;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherDTO {
    private Integer id;
    private String city;
    private String country;
    private Double temperature;

}
