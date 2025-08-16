package com.badfilm.repository;

import com.badfilm.model.BadFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadFilmRepository extends JpaRepository<BadFilm, Long> {
    List<BadFilm> findAllByWinnerIsTrue();
}

