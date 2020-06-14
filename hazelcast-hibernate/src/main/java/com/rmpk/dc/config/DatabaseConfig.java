package com.rmpk.dc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rmpk.dc.model.Movie;
import com.rmpk.dc.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DatabaseConfig {

	@Bean
	@Autowired
	CommandLineRunner initDatabase(MovieRepository movieRepository) {
		return args->{
			movieRepository.deleteAll();
			log.info("Loading : ", movieRepository.save(new Movie("Hello")));
			log.info("Loading : ", movieRepository.save(new Movie("World")));
			log.info("Loading : ", movieRepository.save(new Movie("TEst")));
			log.info("Loading : ", movieRepository.save(new Movie("CAche")));
			log.info("Loading : ", movieRepository.save(new Movie("Application")));
		};
	}
}
