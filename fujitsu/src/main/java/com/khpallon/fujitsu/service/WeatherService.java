package com.khpallon.fujitsu.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.khpallon.fujitsu.dto.ObservationsDTO;
import com.khpallon.fujitsu.dto.StationDTO;
import com.khpallon.fujitsu.model.WeatherEntity;
import com.khpallon.fujitsu.repository.WeatherDataRepository;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Service responsible for fetching weather data from an external API, storing it in the database, and providing access to the stored data.
 * It uses WebClient for non-blocking API calls and is scheduled to run at application startup and at regular intervals defined by a cron expression.
 */

@Service
public class WeatherService {

    private final WebClient webClient;
    private final WeatherDataRepository repository;

    // List of required station names to filter the API data.

    private static final List<String> REQUIRED_STATIONS = List.of(
        "Tallinn-Harku",
        "Tartu-Tõravere",
        "Pärnu"
    );

    public WeatherService(WebClient webClient, WeatherDataRepository repository) {
        this.webClient = webClient;
        this.repository = repository;
    }

    @PostConstruct
    public void onStartup() {
        fetchAndStoreWeatherData().subscribe();
    }

    
    @Scheduled(cron = "${weather.import.cron}")
    public void scheduledImport() {
        fetchAndStoreWeatherData().subscribe();
    }

    // Fetch weather data from the API, filter for required stations, convert to entities, and save to the database in a non-blocking way.

    public Mono<Void> fetchAndStoreWeatherData() {
        return fetchStationsFromApi()
            .flatMapMany(Flux::fromIterable)
            .map(this::mapToEntity)
            .flatMap(entity -> Mono.fromCallable(() -> repository.save(entity))
                                   .subscribeOn(Schedulers.boundedElastic()))
            .then();
    }

    // Fetch the latest weather data for all stations from the external API and filter for the required stations.

    private Mono<List<StationDTO>> fetchStationsFromApi() {
        return webClient.get()
            .uri("https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php")
            .retrieve()
            .bodyToMono(ObservationsDTO.class)
            .map(obs -> obs.getStations().stream()
                .filter(s -> REQUIRED_STATIONS.contains(s.getName()))
                .peek(s -> s.setTimestamp(obs.getTimestamp())) // attach timestamp directly
                .toList()
            );
    }

    // Convert a StationDTO to a WeatherEntity for database storage.

    private WeatherEntity mapToEntity(StationDTO dto) {
        WeatherEntity entity = new WeatherEntity();
        entity.setStationName(dto.getName());
        entity.setWmocode(dto.getWmocode());
        entity.setAirtemperature(dto.getAirtemperature());
        entity.setWindspeed(dto.getWindspeed());
        entity.setPhenomenon(dto.getPhenomenon());
        entity.setTimestamp(dto.getTimestamp());
        return entity;
    }

    // Retrieve all stored weather data from the database.

    public List<WeatherEntity> getAll() {
        return repository.findAll();
    }
}