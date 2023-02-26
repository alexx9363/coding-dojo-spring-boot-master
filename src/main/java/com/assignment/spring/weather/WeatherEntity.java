package com.assignment.spring.weather;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "weather")
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String city;
    private String country;
    private Double temperature;
}