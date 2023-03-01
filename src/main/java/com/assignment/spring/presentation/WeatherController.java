package com.assignment.spring.presentation;

import com.assignment.spring.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.assignment.spring.presentation.InputValidations.IS_ONLY_LETTERS_WITH_SPACES_AND_DASHES_IN_BETWEEN;
import static com.assignment.spring.presentation.InputValidations.MAX_CITY_LENGTH;

@Validated
@RestController("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherMapper weatherMapper;

    @GetMapping
    public ResponseEntity<WeatherDTO> getWeather(
            @Pattern(regexp = IS_ONLY_LETTERS_WITH_SPACES_AND_DASHES_IN_BETWEEN, message = "has bad format")
            @Size(max = MAX_CITY_LENGTH, message = "is too long")
            @RequestParam String city) {
        return ResponseEntity.ok(weatherMapper.getDTOFromEntity(weatherService.getWeather(city)));
    }
}
