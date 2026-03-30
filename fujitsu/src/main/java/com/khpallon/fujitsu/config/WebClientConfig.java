package com.khpallon.fujitsu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * Configuration class for WebClient to fetch weather data from the API.
 * Sets up a WebClient bean with appropriate settings for XML decoding.
 */


@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {

        // Configuration for WebClient with JAXB XML decoder

        ExchangeStrategies strategies = ExchangeStrategies.builder()
            .codecs(configurer -> configurer
                .defaultCodecs()
                .jaxb2Decoder(new Jaxb2XmlDecoder()))
            .build();

        // Build and return the WebClient instance

        return WebClient.builder()
            .exchangeStrategies(strategies)
            .build();
    }
}
