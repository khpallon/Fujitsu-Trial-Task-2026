package com.khpallon.fujitsu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khpallon.fujitsu.dto.DeliveryFeeDTO;
import com.khpallon.fujitsu.enums.*;
import com.khpallon.fujitsu.service.DeliveryFeeService;

/**
 * Controller for handling API requests related to delivery fee calculations based on city and vehicle type.
 */

@RestController
@RequestMapping("/fee")
public class DeliveryFeeController {

    private final DeliveryFeeService deliveryFeeService;

    public DeliveryFeeController(DeliveryFeeService deliveryFeeService) {
        this.deliveryFeeService = deliveryFeeService;
    }

    /**
     * Endpoint to calculate the delivery fee based on city and vehicle type provided as query parameters.
     * @param city - the city for which to calculate the fee (e.g., "Tallinn", "Tartu", "Parnu")
     * @param vehicle - the type of vehicle used for delivery (e.g., "Car", "Scooter", "Bike")
     * @return a DeliveryFeeDTO containing the calculated fee and related information
     */

    @GetMapping
    public DeliveryFeeDTO getFee(@RequestParam String city, @RequestParam String vehicle) {
        City cityEnum = City.from(city);
        Vehicle vehicleEnum = Vehicle.from(vehicle);
        return deliveryFeeService.calculateFee(cityEnum, vehicleEnum);
    }


}