package com.rmpk.dc.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.rmpk.dc.exception.MovieNotFoundException;
import com.rmpk.dc.model.Movie;
import com.rmpk.dc.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
@CacheConfig(cacheNames = "movieMap")
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private MovieRepository movieRepository;

	@Cacheable("movieMap")
	@Override
	public Movie findByName(String name) {
		log.info("Movie {}", name);
		return movieRepository.findByName(name)
				.orElseThrow(()-> new MovieNotFoundException());
	}

	@Caching(evict = {
            @CacheEvict(key = "'md5:'+#result.md5", condition = "#result!=null"),
            @CacheEvict(key = "'name:'+#name"),
            @CacheEvict(key = "'name-or-md5:'+#name"),
            @CacheEvict(key = "'name-or-md5:'+#name")
    })
	@Override
	public Movie deleteByName(String name) {
		movieRepository.deleteByName(name);
		return findByName(name);
	}

}
