package com.assignment.spring.presentation;

import com.assignment.spring.service.WeatherService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @Mock
    private WeatherMapper weatherMapper;

    @InjectMocks
    private WeatherController weatherController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
    }

    @Test
    public void getWeatherByCityTest() throws Exception {
        String city = "Bucharest";
        WeatherDTO dto = WeatherDTO.builder().id(1).temperature(280.16d).country("RO").city(city).build();
        Mockito.when(weatherMapper.getDTOFromEntity((any()))).thenReturn(dto);
        mockMvc.perform(get("/weather?city=" + city).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(dto.getId())))
                .andExpect(jsonPath("$.temperature", is(dto.getTemperature())))
                .andExpect(jsonPath("$.country", is(dto.getCountry())))
                .andExpect(jsonPath("$.city", is(dto.getCity())));
    }
}
