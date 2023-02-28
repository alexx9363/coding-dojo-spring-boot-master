package com.assignment.spring.api;

import com.assignment.spring.api.response.Main;
import com.assignment.spring.api.response.Sys;
import com.assignment.spring.api.response.WeatherResponse;
import com.assignment.spring.weather.TimeProvider;
import com.assignment.spring.weather.WeatherEntity;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = OpenWeatherMapService.class)
public class OpenWeatherMapServiceTest extends TestCase {
    @Mock
    private OpenWeatherMapRest openWeatherMapRest;
    @Mock
    private TimeProvider timeProvider;
    @InjectMocks
    private OpenWeatherMapService openWeatherMapService;
    private static final LocalDateTime NOW = LocalDateTime.now();

    @Before
    public void setUp() {
        Mockito.when(timeProvider.getNow()).thenReturn(NOW);
    }

    @Test
    public void getWeatherFromOpenWeatherMapTest() {
        WeatherResponse response = new WeatherResponse();
        String city = "Bucharest";
        response.setName(city);
        Sys sys = new Sys();
        String country = "RO";
        sys.setCountry(country);
        response.setSys(sys);
        Main main = new Main();
        Double temperature = 280.10d;
        main.setTemp(temperature);
        response.setMain(main);

        Mockito.when(openWeatherMapRest.getWeather(city)).thenReturn(response);

        WeatherEntity actual = openWeatherMapService.getWeatherFromOpenWeatherMap(city);

        Assertions.assertEquals(city, actual.getCity());
        assertEquals(country, actual.getCountry());
        assertEquals(temperature, actual.getTemperature());
        assertEquals(NOW, actual.getUpdatedOn());
    }
}