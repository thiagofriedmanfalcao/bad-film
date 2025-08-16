package com.badfilm;

import com.badfilm.service.BadFilmService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BadFilmApplication {
    @Autowired
    private BadFilmService badFilmService;

    public static void main(String[] args) {
        SpringApplication.run(BadFilmApplication.class, args);
    }

    @PostConstruct
    private void postConstruct() {
        badFilmService.readMovieListCsvAndSaveOnDatabase();
    }

}
