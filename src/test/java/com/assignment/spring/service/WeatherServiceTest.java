package com.assignment.spring.service;

import com.assignment.spring.domain.WeatherEntity;
import com.assignment.spring.infrastructure.TimeProvider;
import com.assignment.spring.infrastructure.weather.WeatherRepository;
import com.assignment.spring.infrastructure.api.OpenWeatherMapService;
import com.assignment.spring.service.WeatherService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = WeatherService.class)
public class WeatherServiceTest {

    @Mock
    private OpenWeatherMapService openWeatherMapService;
    @Mock
    private WeatherRepository weatherRepository;
    @Mock
    private TimeProvider timeProvider;
    @InjectMocks
    private WeatherService weatherService;

    private static final LocalDateTime NOW = LocalDateTime.now();

    private final int weatherUpdateInterval = 10;

    @Before
    public void setUp() {
        Mockito.when(timeProvider.getNow()).thenReturn(NOW);
        ReflectionTestUtils.setField(weatherService, "weatherUpdateInterval", weatherUpdateInterval);
    }

    @Test
    public void getWeatherTest_isPresentAndUpToDate() {
        String city = "Bucharest";
        WeatherEntity expected = WeatherEntity.builder().id(1).temperature(280.16d).country("RO").city(city)
                .updatedOn(NOW.minusMinutes(5)).build();
        Mockito.when(weatherRepository.findByCityIgnoreCase(city)).thenReturn(Optional.of(expected));

        WeatherEntity actual = weatherService.getWeather(city);

        assertEquals(expected, actual);
    }

    @Test
    public void getWeatherTest_isPresentAnd_NOT_UpToDate() {
        String city = "Bucharest";
        WeatherEntity entityPresentInRepo = WeatherEntity.builder().id(1).temperature(280.16d).country("RO").city(city)
                .updatedOn(NOW.minusMinutes(weatherUpdateInterval+1)).build();
        Mockito.when(weatherRepository.findByCityIgnoreCase(city)).thenReturn(Optional.of(entityPresentInRepo));
        WeatherEntity expected = WeatherEntity.builder().id(1).temperature(280.16d).country("RO").city(city)
                .updatedOn(NOW).build();
        Mockito.when(openWeatherMapService.getWeatherFromOpenWeatherMap(city)).thenReturn(expected);
        Mockito.when(weatherRepository.save(expected)).thenReturn(expected);

        WeatherEntity actual = weatherService.getWeather(city);

        assertEquals(expected, actual);
    }

    @Test
    public void getWeatherTest_is_NOT_Present() {
        String city = "Bucharest";
        Mockito.when(weatherRepository.findByCityIgnoreCase(city)).thenReturn(Optional.empty());
        WeatherEntity expected = WeatherEntity.builder().id(1).temperature(280.16d).country("RO").city(city)
                .updatedOn(NOW).build();
        Mockito.when(openWeatherMapService.getWeatherFromOpenWeatherMap(city)).thenReturn(expected);
        Mockito.when(weatherRepository.save(expected)).thenReturn(expected);

        WeatherEntity actual = weatherService.getWeather(city);

        assertEquals(expected, actual);
    }
}