package com.hifood;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hifood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class HifoodApplication {

	public static void main(String[] args) {
		
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(HifoodApplication.class, args);
	}

}
