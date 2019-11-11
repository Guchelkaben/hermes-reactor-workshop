package de.mmeier.reactortesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ReactortestingApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReactortestingApplication.class, args);
	}
}
