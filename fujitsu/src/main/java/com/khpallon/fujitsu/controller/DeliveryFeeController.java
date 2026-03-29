package com.khpallon.fujitsu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khpallon.fujitsu.dto.DeliveryFeeDTO;
import com.khpallon.fujitsu.service.DeliveryFeeService;

/**
 * Controller for handling API requests related to delivery fee calculations.
 */
@RestController
@RequestMapping("/fee")
public class DeliveryFeeController {

    private final DeliveryFeeService deliveryFeeService;

    public DeliveryFeeController(DeliveryFeeService deliveryFeeService) {
        this.deliveryFeeService = deliveryFeeService;
    }

    @GetMapping
    public DeliveryFeeDTO getFee(@RequestParam String city, @RequestParam String vehicle) {
        return deliveryFeeService.calculateFee(city, vehicle);
    }


}