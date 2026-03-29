package com.khpallon.fujitsu.service;

import org.springframework.stereotype.Service;

import com.khpallon.fujitsu.config.StationMapping;
import com.khpallon.fujitsu.dto.DeliveryFeeDTO;
import com.khpallon.fujitsu.enums.*;
import com.khpallon.fujitsu.model.WeatherEntity;
import com.khpallon.fujitsu.repository.WeatherDataRepository;


@Service
public class DeliveryFeeService {

    private final BaseFeeService baseFeeService;
    private final ExtraFeeService extraFeeService;
    private final WeatherDataRepository repo;
    private final StationMapping stationMapping;

    public DeliveryFeeService(BaseFeeService baseFeeService, ExtraFeeService extraFeeService, WeatherDataRepository repo, StationMapping stationMapping) {
        this.baseFeeService = baseFeeService;
        this.extraFeeService = extraFeeService;
        this.repo = repo;
        this.stationMapping = stationMapping;
    }

    // Main method to calculate the delivery fee based on the city and vehicle type

    public DeliveryFeeDTO calculateFee(City city, Vehicle vehicle) {

        String station = stationMapping.getStationForCity(city.name().toLowerCase());

        WeatherEntity weather = repo.findTopByStationNameOrderByTimestampDesc(station)
        .orElseThrow(
            () -> new IllegalStateException("No weather data available for station: " + station)
        );


        double base = baseFeeService.calculate(city, vehicle);
        double extra = extraFeeService.calculate(vehicle, weather);

        return new DeliveryFeeDTO(city, vehicle, base + extra);
    }


}
