package com.assignment.spring.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

//    @RequestMapping
//    public WeatherEntity weather(HttpServletRequest request) {
//        String city = request.getParameter("city");
//        return weatherService.getWeather(city);
//    }

    @GetMapping
    public WeatherDTO getWeather(String city) {
        return WeatherMapper.getDTOFromEntity(weatherService.getWeather(city));
    }
}
