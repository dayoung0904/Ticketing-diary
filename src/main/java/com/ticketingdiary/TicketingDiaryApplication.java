package com.ticketingdiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TicketingDiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketingDiaryApplication.class, args);
		
		
	}

}
