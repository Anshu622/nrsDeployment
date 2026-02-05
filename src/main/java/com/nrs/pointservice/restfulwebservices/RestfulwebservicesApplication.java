package com.nrs.pointservice.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class RestfulwebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulwebservicesApplication.class, args);
	}

}
