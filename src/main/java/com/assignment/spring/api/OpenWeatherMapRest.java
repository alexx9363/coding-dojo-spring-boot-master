package com.assignment.spring.api;

import com.assignment.spring.Constants;
import com.assignment.spring.api.exception.OpenWeatherNotFoundException;
import com.assignment.spring.api.exception.OpenWeatherUnexpectedException;
import com.assignment.spring.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherMapRest {

    private static final String URL = Constants.WEATHER_API_URL.replace("{appid}", Constants.APP_ID);

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        try {
            ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(URL.replace("{city}", city), WeatherResponse.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new OpenWeatherNotFoundException(city);
        } catch (RestClientException re) {
            throw new OpenWeatherUnexpectedException();
        }
    }
}
