package com.khpallon.fujitsu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpallon.fujitsu.model.WeatherEntity;

/**
 * Repository interface for performing CRUD operations on WeatherEntity.
 */

public interface WeatherDataRepository extends JpaRepository<WeatherEntity, Long> {
    Optional<WeatherEntity> findTopByStationNameOrderByTimestampDesc(String stationName);
}

