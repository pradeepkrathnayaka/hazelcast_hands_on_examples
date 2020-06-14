package com.rmpk.dc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rmpk.dc.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	public Optional<Movie> findByName(String name);

	public void deleteByName(String name);
}
