package com.khpallon.fujitsu.service;

import com.khpallon.fujitsu.config.StationMapping;
import com.khpallon.fujitsu.dto.DeliveryFeeDTO;
import com.khpallon.fujitsu.enums.City;
import com.khpallon.fujitsu.enums.Vehicle;
import com.khpallon.fujitsu.exception.WeatherDataNotFoundException;
import com.khpallon.fujitsu.model.WeatherEntity;
import com.khpallon.fujitsu.repository.WeatherDataRepository;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeliveryFeeServiceTest {

    private BaseFeeService baseFeeService = mock(BaseFeeService.class);
    private ExtraFeeService extraFeeService = mock(ExtraFeeService.class);
    private WeatherDataRepository weatherRepository = mock(WeatherDataRepository.class);
    private StationMapping stationMapping = mock(StationMapping.class);

    private DeliveryFeeService service = new DeliveryFeeService(
        baseFeeService, extraFeeService, weatherRepository, stationMapping
    );

    @Test
    void throwsWhenWeatherNotFound() {
        when(stationMapping.getStationForCity(City.TARTU)).thenReturn("Tartu-Station");
        when(weatherRepository.findTopByStationNameOrderByTimestampDesc("Tartu-Station"))
            .thenReturn(Optional.empty());

        assertThrows(WeatherDataNotFoundException.class,
            () -> service.calculateFee(City.TARTU, Vehicle.BIKE));
    }

    @Test
    void calculatesTotalFeeCorrectly() {
        WeatherEntity weather = new WeatherEntity();
        when(stationMapping.getStationForCity(City.TARTU)).thenReturn("Tartu-Station");
        when(weatherRepository.findTopByStationNameOrderByTimestampDesc("Tartu-Station"))
            .thenReturn(Optional.of(weather));

        when(baseFeeService.calculate(City.TARTU, Vehicle.BIKE)).thenReturn(2.5);
        when(extraFeeService.calculate(Vehicle.BIKE, weather)).thenReturn(1.0);

        DeliveryFeeDTO dto = service.calculateFee(City.TARTU, Vehicle.BIKE);

        assertEquals(3.5, dto.getFee());
        assertEquals(City.TARTU, dto.getCity());
        assertEquals(Vehicle.BIKE, dto.getVehicle());
    }
}
