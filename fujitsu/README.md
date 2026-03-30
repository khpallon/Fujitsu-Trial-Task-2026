# Fujitsu Delivery Fee Service

A Spring Boot application implementing a sub‑module of a food delivery system. It calculates delivery fees based on **city**, **vehicle type**, and **latest weather conditions** imported from the Estonian Environment Agency.


## Features

- Scheduled import of weather observations (configurable cron)
- Historical weather data stored in H2 database
- Rule‑based delivery fee calculation
- Clean layered architecture
- Enum‑based city & vehicle validation
- Global error handling with custom exceptions
- REST API for fee calculation
- Unit tests for rule classes and services


## Tech stack

- Java 26
- Spring Boot 4
- H2 Database
- Jackson XML

## Running the Application

In bash:

- cd fujitsu/
- ./gradlew bootRun


## Weather Import

### Source
Estonian Environment Agency XML feed:  
https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php

### Stations imported
- Tallinn‑Harku → Tallinn  
- Tartu‑Tõravere → Tartu  
- Pärnu → Pärnu  

### How it works
- Runs once at startup (@PostConstruct)
- Runs on schedule (@Scheduled)
- Cron expression is configurable:

weather.import.cron=0 15 * * * *

### Implementation notes
- Uses WebClient (non‑blocking)
- Uses Reactor (Mono, Flux)
- Saves history
- Maps XML → DTO → WeatherEntity → H2


## Delivery Fee Calculation

### Base Fee
Handled by BaseFeeService using enums + switch logic.

### Extra Fees
Handled by ExtraFeeService, which delegates to rule classes:

- FeeRule
- TemperatureRule  
- WindSpeedRule  
- PhenomenonRule  

Each rule:
- Checks applicability  
- Returns fee or throws exception (for forbidden conditions)



## REST API

### Calculate Delivery Fee Example
GET /fee?city=Tallinn&vehicle=Scooter

### Query Parameters
city: Tallinn, Tartu, Parnu  
vehicle: Car, Scooter, Bike  

### Success Response Example

```xml
<DeliveryFeeDTO>
    <city>TALLINN</city>
    <vehicle>SCOOTER</vehicle>
    <fee>4.0</fee>
</DeliveryFeeDTO>

## Testing

Unit tests exist for:

- Services
- Rules

Run tests:
./gradlew test


### Design Decisions

**Enum‑based city and vehicle types**  
Enums keep the input clean and predictable, so the app never deals with random strings or typos.

**Rule classes for clean separation**  
Each weather rule lives in its own class, which keeps the logic easy to read and test.

**Reactive WebClient for non‑blocking imports**  
The weather import runs smoothly without blocking threads, which keeps the scheduler responsive.

**StationMapping config for clean city → station mapping**  
The mapping between business cities and API station names is in one place instead of scattered across the code.

**Global exception handling**  
All errors are formatted the same way, and controllers stay clean without repetitive try/catch blocks.


