package com.khpallon.fujitsu.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.khpallon.fujitsu.dto.ObservationsDTO;
import com.khpallon.fujitsu.dto.StationDTO;
import com.khpallon.fujitsu.model.WeatherEntity;
import com.khpallon.fujitsu.repository.WeatherDataRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

/**
 * Service class for fetching weather data from the API and storing it in the database.
 */

@Service
public class WeatherService {

    private final WebClient webClient;
    private final WeatherDataRepository repository;

    // List of required station names to filter the API data.

    private static final List<String> REQUIRED = List.of(
        "Tallinn-Harku",
        "Tartu-Tõravere",
        "Pärnu"
    );

    public WeatherService(WebClient webClient, WeatherDataRepository repository) {
        this.webClient = webClient;
        this.repository = repository;
    }

    // Scheduled method to fetch data every 15 seconds and store it in the database.

    @Scheduled(cron = "*/15 * * * * *")
    public void init() {
        fetchData().subscribe();
    }

    // Fetch weather data from the API, filter for required stations, convert to entities, and save to the database.

    public Mono<Void> fetchData() {
    return fetchRequiredStations()
        .flatMapMany(Flux::fromIterable)
        .map(tuple -> toEntity(tuple.getT1(), tuple.getT2()))
        .flatMap(entity -> Mono.fromCallable(() -> repository.save(entity)))
        .then();
    }

    // Fetch weather data from the API and filter for required stations.

    public Mono<List<Tuple2<StationDTO, ObservationsDTO>>> fetchRequiredStations() {
        return webClient.get()
            .uri("https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php")
            .retrieve()
            .bodyToMono(ObservationsDTO.class)
            .map(obs -> obs.getStations().stream()
                .filter(s -> REQUIRED.contains(s.getName()))
                .map(s -> Tuples.of(s, obs))
                .toList()
            );
    }

    // Convert StationDTO and ObservationsDTO to WeatherEntity for database storage.    

    public WeatherEntity toEntity(StationDTO dto, ObservationsDTO obs) {
        WeatherEntity entity = new WeatherEntity();
        entity.setName(dto.getName());
        entity.setWmocode(dto.getWmocode());
        entity.setAirtemperature(dto.getAirtemperature());
        entity.setWindspeed(dto.getWindspeed());
        entity.setPhenomenon(dto.getPhenomenon());
        entity.setTimestamp(obs.getTimestamp());
        return entity;
    }

    // Retrieve all stored weather data from the database.

    public List<WeatherEntity> getAll() {
        return repository.findAll();
    }
}