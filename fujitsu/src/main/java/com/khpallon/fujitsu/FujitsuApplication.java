package com.khpallon.fujitsu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Main application class for the Fujitsu weather data application.
 * This class bootstraps the Spring Boot application.
 */

@SpringBootApplication
public class FujitsuApplication {

	public static void main(String[] args) {
		SpringApplication.run(FujitsuApplication.class, args);
	}

}
