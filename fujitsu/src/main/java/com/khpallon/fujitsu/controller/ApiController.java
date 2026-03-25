package com.khpallon.fujitsu.controller;

import org.springframework.web.bind.annotation.*;

import com.khpallon.fujitsu.model.WeatherEntity;
import com.khpallon.fujitsu.service.ApiService;

import java.util.List;


/**
 * Controller for handling API requests for the weather data.
 */

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    // Endpoint to fetch data from the API and store it in the database
    @GetMapping("/fetch")
    public WeatherEntity fetchData() {
        return apiService.fetchData();
    }

    // Endpoint to retrieve all stored weather data from the database
    @GetMapping("/data")
    public List<WeatherEntity> getStoredData() {
        return apiService.getAll();
    }
}

