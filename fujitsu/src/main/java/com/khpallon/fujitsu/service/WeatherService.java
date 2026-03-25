package com.khpallon.fujitsu.service;

import java.io.StringReader;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.khpallon.fujitsu.dto.ObservationsDTO;
import com.khpallon.fujitsu.dto.StationDTO;
import com.khpallon.fujitsu.model.WeatherEntity;
import com.khpallon.fujitsu.repository.WeatherDataRepository;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;



/**
 * Service class for fetching weather data from the API and storing it in the database.
 */

@Service
public class WeatherService {
    
    private final WebClient webClient;
    private final WeatherDataRepository repository;

    public WeatherService(WebClient webClient, WeatherDataRepository repository) {
        this.webClient = webClient;
        this.repository = repository;
    }

    // Fetch data from the API, convert it to entities, and store in the database

    public WeatherEntity fetchData() {

        ObservationsDTO observations = fetchWeather();

        observations.getStations().forEach(station -> {
            WeatherEntity entity = toEntity(station, observations.getTimestamp());
            repository.save(entity);
        });

        return toEntity(observations.getStations().get(0), observations.getTimestamp()); // Return first station as example
 
    }

    // Fetch weather data from the API and return it as a DTO.

    public ObservationsDTO fetchWeather() {
        String xml = webClient.get()
                .uri("https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return parseXml(xml);
    }

    // Parse the XML response from the API into ObservationsDTO.

    private ObservationsDTO parseXml(String xml) {
    try {
        JAXBContext context = JAXBContext.newInstance(ObservationsDTO.class, StationDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (ObservationsDTO) unmarshaller.unmarshal(new StringReader(xml));
    } catch (Exception e) {
        System.out.println("XML parsing failed: " + e.getMessage());
        throw new RuntimeException("Failed to parse XML", e);
    }
}
    // Convert a StationDTO to a WeatherEntity for the database.    

    private WeatherEntity toEntity(StationDTO dto, String timestamp) {
        WeatherEntity entity = new WeatherEntity();
        entity.setName(dto.getName());
        entity.setWmocode(dto.getWmocode());
        entity.setAirtemperature(dto.getAirtemperature());
        entity.setWindspeed(dto.getWindspeed());
        entity.setPhenomenon(dto.getPhenomenon());
        entity.setTimestamp(timestamp);
        return entity;
    }

    // Retrieve all stored weather data from the database.

    public List<WeatherEntity> getAll() {
        return repository.findAll();
    }
}