package com.khpallon.fujitsu.controller;

import org.springframework.web.bind.annotation.*;

import com.khpallon.fujitsu.model.WeatherEntity;
import com.khpallon.fujitsu.service.WeatherService;

import java.util.List;


/**
 * Controller for handling API requests for the weather data.
 */

@RestController
@RequestMapping("/api")
public class ApiController {

    private final WeatherService apiService;

    public ApiController(WeatherService apiService) {
        this.apiService = apiService;
    }

    // Endpoint to retrieve all stored weather data from the database
    @GetMapping("/data")
    public List<WeatherEntity> getStoredData() {
        return apiService.getAll();
    }
}

