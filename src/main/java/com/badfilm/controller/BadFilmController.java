package com.badfilm.controller;

import com.badfilm.service.BadFilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bad-films")
public class BadFilmController {
    private final BadFilmService badFilmService;

    public BadFilmController(BadFilmService badFilmService) {
        this.badFilmService = badFilmService;
    }

    @GetMapping
    public ResponseEntity<?> getAllBadFilms() {
        return ResponseEntity.ok(badFilmService.findAll());
    }

    @GetMapping("/interval-winners")
    public ResponseEntity<?> getIntervalWinners() {
        return ResponseEntity.ok(badFilmService.getIntervalWinners());
    }
}