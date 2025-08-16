package com.badfilm.service;

import com.badfilm.model.BadFilm;

import java.util.List;
import java.util.Map;

public interface BadFilmService {
    List<BadFilm> findAll();

    void readMovieListCsvAndSaveOnDatabase();

    Map<String, Object> getIntervalWinners();
}
