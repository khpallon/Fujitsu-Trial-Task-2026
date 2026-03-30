package com.khpallon.fujitsu.service;

import org.springframework.stereotype.Service;

import com.khpallon.fujitsu.config.StationMapping;
import com.khpallon.fujitsu.dto.DeliveryFeeDTO;
import com.khpallon.fujitsu.enums.*;
import com.khpallon.fujitsu.exception.WeatherDataNotFoundException;
import com.khpallon.fujitsu.model.WeatherEntity;
import com.khpallon.fujitsu.repository.WeatherDataRepository;

/**
 * Service for calculating the total delivery fee based on city, vehicle, and weather conditions.
 */

@Service
public class DeliveryFeeService {

    private final BaseFeeService baseFeeService;
    private final ExtraFeeService extraFeeService;
    private final WeatherDataRepository weatherRepository;
    private final StationMapping stationMapping;

    public DeliveryFeeService(BaseFeeService baseFeeService, ExtraFeeService extraFeeService, WeatherDataRepository weatherRepository, StationMapping stationMapping) {
        this.baseFeeService = baseFeeService;
        this.extraFeeService = extraFeeService;
        this.weatherRepository = weatherRepository;
        this.stationMapping = stationMapping;
    }

    public DeliveryFeeDTO calculateFee(City city, Vehicle vehicle) {

        // 1. Resolve the weather station for the city
        String stationName = stationMapping.getStationForCity(city);

        // 2. Fetch the latest weather data for that station
        WeatherEntity latestWeather = weatherRepository
                .findTopByStationNameOrderByTimestampDesc(stationName)
                .orElseThrow(() -> new WeatherDataNotFoundException(stationName));

        // 3. Calculate base and extra fees
        double baseFee = baseFeeService.calculate(city, vehicle);
        double extraFee = extraFeeService.calculate(vehicle, latestWeather);

        // 4. Return the total fee
        return new DeliveryFeeDTO(city, vehicle, baseFee + extraFee);
    }


}
