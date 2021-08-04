package com.rabbit.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProducerRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerRabbitApplication.class, args);
	}

}
