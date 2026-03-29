package com.khpallon.fujitsu.service;

import org.springframework.stereotype.Service;

import com.khpallon.fujitsu.config.StationMapping;
import com.khpallon.fujitsu.dto.DeliveryFeeDTO;
import com.khpallon.fujitsu.repository.WeatherDataRepository;

@Service
public class DeliveryFeeService {

    private final WeatherDataRepository repo;
    private final StationMapping stationMapping;

    public DeliveryFeeService(WeatherDataRepository repo, StationMapping stationMapping) {
        this.repo = repo;
        this.stationMapping = stationMapping;
    }

    // Main method to calculate the delivery fee based on the city and vehicle type

    public DeliveryFeeDTO calculateFee(String city, String vehicle) {

        if (city == null || vehicle == null) {
            throw new IllegalArgumentException("City and vehicle must not be null");
        }

        String station = stationMapping.getStationForCity(city.toLowerCase());
        if (station == null) {
            throw new IllegalArgumentException("Unknown city: " + city);
        }

        double baseFee = rbf(city, vehicle);

        if (vehicle.equalsIgnoreCase("scooter") || vehicle.equalsIgnoreCase("bike")) {
            baseFee += atef(station) + wpef(station);

        }

        if (vehicle.equalsIgnoreCase("bike")) {
            baseFee += wsef(station);
        }

        return new DeliveryFeeDTO(city, vehicle, baseFee);
    }


    // Method to calculate the base fee (RBF) based on the city and vehicle type

    public double rbf(String city, String vehicle) {

        double vehicleFee = switch (vehicle.toLowerCase()) {
            case "car" -> 3.0;
            case "scooter" -> 2.5;
            case "bike" -> 2.0;
            default -> throw new IllegalArgumentException("Unknown vehicle: " + vehicle);
        };

        double cityMultiplier = switch (city.toLowerCase()) {
            case "tallinn" -> 2.0;
            case "tartu" -> 1.5;
            case "pärnu" -> 1.0;
            default -> throw new IllegalArgumentException("Unknown city: " + city);
        };

        return vehicleFee * cityMultiplier;
    }

    // Method to calculate the additional temperature fee (ATEF) based on the latest weather data

    public double atef(String station){
        return repo.findTopByStationNameOrderByTimestampDesc(station)
            .map(weather -> {
                double temp = weather.getAirtemperature();
                if (temp < -10.0) {
                    return 1.0;
                } else if (temp >= -10 && temp < 0) {
                    return 0.5;
                } else {
                    return 0.0;
                }
            })
            .orElseThrow(() -> new IllegalStateException("No weather data available for station: " + station));
            }

    
    // Method to calculate the wind speed extra fee (WSEF) based on the latest weather data
    
    public double wsef(String station){
        return repo.findTopByStationNameOrderByTimestampDesc(station)
            .map(weather -> {
                double windspeed = weather.getWindspeed();
                if (windspeed >= 10.0 && windspeed <= 20.0) {
                    return 0.5;
                } else if (windspeed > 20.0) {
                    throw new IllegalStateException("Usage of selected vehicle type is forbidden");
                } else {
                    return 0.0;
                }
            })
            .orElseThrow(() -> new IllegalStateException("No weather data available for station: " + station));
    }

    // Method to calculate the weather phenomenon extra fee (WPEF) based on the latest weather data

    public double wpef(String station) {
        return repo.findTopByStationNameOrderByTimestampDesc(station)
            .map(weather -> {
                String phenomenon = weather.getPhenomenon().toLowerCase();
                if (phenomenon.contains("snow") || phenomenon.contains("sleet")) {
                    return 1.0;
                } else if (phenomenon.contains("rain")) {
                    return 0.5;
                } else if (phenomenon.contains("glaze") || phenomenon.contains("hail") || phenomenon.contains("thunder")) {
                    throw new IllegalStateException("Usage of selected vehicle type is forbidden");
                }
                else {
                    return 0.0;
                }
            })
            .orElseThrow(() -> new IllegalStateException("No weather data available for station: " + station));
    }

}
