CREATE TABLE weatherdata (
    id IDENTITY PRIMARY KEY,
    stationName VARCHAR(255),
    wmocode VARCHAR(255),
    airtemperature DOUBLE,
    windspeed DOUBLE,
    phenomenon VARCHAR(255),
    timestamp TIMESTAMP
);