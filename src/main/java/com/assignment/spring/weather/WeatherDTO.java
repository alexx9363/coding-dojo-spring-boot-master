package com.assignment.spring.weather;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class WeatherDTO {
    private Integer id;
    private String city;
    private String country;
    private Double temperature;

}
