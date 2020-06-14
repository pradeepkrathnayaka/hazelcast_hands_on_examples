package com.rmpk.dc.service;

import com.rmpk.dc.model.Movie;

public interface MovieService {

	Movie findByName(String name);

	Movie deleteByName(String name);

}
