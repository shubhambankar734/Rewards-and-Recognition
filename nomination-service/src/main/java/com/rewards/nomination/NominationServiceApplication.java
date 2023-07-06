package com.rewards.nomination;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.rewards")
@OpenAPIDefinition(info =
@Info(title = "Nomination API", version = "1.0", description = "Documentation Nomination API v1.0")
)
public class NominationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NominationServiceApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplete(){
		return new RestTemplate();
	}
}
