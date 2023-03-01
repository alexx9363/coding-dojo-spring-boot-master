package com.assignment.spring.presentation;

import com.assignment.spring.domain.WeatherEntity;
import com.assignment.spring.presentation.WeatherDTO;
import com.assignment.spring.presentation.WeatherMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = WeatherMapper.class)
public class WeatherMapperTest {

    @InjectMocks
    private WeatherMapper weatherMapper;

    @Test
    public void testGetDTOFromEntity() {
        WeatherEntity entity = WeatherEntity.builder().id(1).temperature(280.16d).country("RO").city("Bucharest").build();

        WeatherDTO actual = weatherMapper.getDTOFromEntity(entity);

        assertEquals(entity.getId(), actual.getId());
        assertEquals(entity.getTemperature(), actual.getTemperature());
        assertEquals(entity.getCountry(), actual.getCountry());
        assertEquals(entity.getCity(), actual.getCity());
    }

    @Test
    public void testGetEntityFromDTO() {
        WeatherDTO dto = WeatherDTO.builder().id(1).temperature(280.16d).country("RO").city("Bucharest").build();

        WeatherEntity actual = weatherMapper.getEntityFromDTO(dto);

        assertEquals(dto.getId(), actual.getId());
        assertEquals(dto.getTemperature(), actual.getTemperature());
        assertEquals(dto.getCountry(), actual.getCountry());
        assertEquals(dto.getCity(), actual.getCity());
    }
}