package com.khpallon.fujitsu.service;

import org.springframework.stereotype.Service;

import com.khpallon.fujitsu.enums.City;
import com.khpallon.fujitsu.enums.Vehicle;


@Service
public class BaseFeeService {

     public double calculate(City city, Vehicle vehicle) {

        double vehicleFee = switch (vehicle) {
            case CAR -> 3.0;
            case SCOOTER -> 2.5;
            case BIKE -> 2.0;
            default -> throw new IllegalArgumentException("Unknown vehicle: " + vehicle);
        };

        double cityMultiplier = switch (city) {
            case TALLINN -> 2.0;
            case TARTU -> 1.0;
            case PARNU -> 0.0;
            default -> throw new IllegalArgumentException("Unknown city: " + city);
        };

        return vehicleFee + (0.5 * cityMultiplier);
    }

    
}
