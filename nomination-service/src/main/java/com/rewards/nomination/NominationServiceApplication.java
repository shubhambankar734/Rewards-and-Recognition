package com.rewards.nomination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.rewards")
public class NominationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NominationServiceApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplete(){
		return new RestTemplate();
	}
}
