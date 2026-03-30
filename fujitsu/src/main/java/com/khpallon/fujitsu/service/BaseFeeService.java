package com.khpallon.fujitsu.service;

import org.springframework.stereotype.Service;

import com.khpallon.fujitsu.enums.City;
import com.khpallon.fujitsu.enums.Vehicle;
import com.khpallon.fujitsu.exception.UnknownCityException;
import com.khpallon.fujitsu.exception.UnknownVehicleException;


/**
 * Service for calculating the base fee for a delivery based on the city and vehicle type.
 */

@Service
public class BaseFeeService {

     public double calculate(City city, Vehicle vehicle) {

        if (vehicle == null) {
            throw new UnknownVehicleException(null);
        }

        if (city == null) {
            throw new UnknownCityException(null);
        }

        double vehicleFee = switch (vehicle) {
            case CAR -> 3.0;
            case SCOOTER -> 2.5;
            case BIKE -> 2.0;
        };

        double cityMultiplier = switch (city) {
            case TALLINN -> 2.0;
            case TARTU -> 1.0;
            case PARNU -> 0.0;
        };

        // Total fee is base vehicle fee plus a multiplier based on the city
        
        return vehicleFee + (0.5 * cityMultiplier);
    }

    
}
