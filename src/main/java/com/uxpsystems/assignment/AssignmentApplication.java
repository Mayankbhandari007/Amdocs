package com.uxpsystems.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableCaching
public class AssignmentApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(AssignmentApplication.class, args);

	}

}
