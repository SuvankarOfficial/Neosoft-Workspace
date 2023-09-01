package com.itinerary.itinerary.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ItineraryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItineraryManagementApplication.class, args);
	}

}
