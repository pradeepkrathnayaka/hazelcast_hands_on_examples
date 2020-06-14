package com.rmpk.dc.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmpk.dc.model.Movie;
import com.rmpk.dc.service.MovieService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/movies")
@Slf4j
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private CacheManager cacheManager;
	
	@PostConstruct
	public void init() {
		log.info("Cache manager info" + cacheManager);
	}

	@PostMapping("/{id}")
	public ResponseEntity<Object> getMovieById(@PathVariable Integer id) {
		log.info("Movie id {}", id);
		return new ResponseEntity<>("Jurrasic park", HttpStatus.OK);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Object> getMovieByName(@PathVariable String name) {
		log.info("Movie name {}", name);
		Movie findByName = movieService.findByName(name);
		System.out.println(findByName.toString());
		return new ResponseEntity<>(findByName.getName(), HttpStatus.OK);
	}
	@DeleteMapping("/{name}")
	public ResponseEntity<Object> deleteMovieByName(@PathVariable String name) {
		log.info("Delete Movie name {}", name);
		Movie findByName = movieService.deleteByName(name);
		System.out.println(findByName.toString());
		return new ResponseEntity<>(findByName.getName(), HttpStatus.OK);
	}

}
