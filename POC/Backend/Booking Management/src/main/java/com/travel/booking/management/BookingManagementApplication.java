package com.travel.booking.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookingManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingManagementApplication.class, args);
	}

}
