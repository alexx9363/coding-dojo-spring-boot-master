package com.assignment.spring.infrastructure.api;

import com.assignment.spring.infrastructure.api.response.WeatherResponse;
import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = OpenWeatherMapRest.class)
public class OpenWeatherMapRestTest extends TestCase {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OpenWeatherMapRest openWeatherMapRest;

    @Test
    public void testTest() {
        String url = "/weather?q={city}&APPID={appid}";
        ReflectionTestUtils.setField(openWeatherMapRest, "weatherApiUrl", url);
        String appId = "abc1234";
        ReflectionTestUtils.setField(openWeatherMapRest, "appId", appId);
        String city = "Bucharest";
        WeatherResponse expected = new WeatherResponse();
        expected.setName(city);
        Mockito.when(restTemplate.getForEntity(url.replace("{appid}", appId).replace("{city}", city), WeatherResponse.class)).thenReturn(new ResponseEntity<>(expected, HttpStatus.OK));

        WeatherResponse actual = openWeatherMapRest.getWeather(city);

        Assertions.assertEquals(expected.getName(), actual.getName());
    }
}