package com.assignment.spring.infrastructure.api;

import com.assignment.spring.infrastructure.api.exception.OpenWeatherNotFoundException;
import com.assignment.spring.infrastructure.api.exception.OpenWeatherUnexpectedException;
import com.assignment.spring.infrastructure.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherMapRest {

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    @Value("${weather.api.appid}")
    private String appId;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        try {
            String url = weatherApiUrl.replace("{appid}", appId).replace("{city}", city);
            return restTemplate.getForEntity(url, WeatherResponse.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new OpenWeatherNotFoundException(city);
        } catch (RestClientException re) {
            throw new OpenWeatherUnexpectedException();
        }
    }
}
