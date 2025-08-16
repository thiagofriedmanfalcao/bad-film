package com.badfilm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
public class BadFilm {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long badFilmId;

    @Column(nullable = false)
    private Integer year;

    @Column
    private String title;

    @Column
    private String studios;

    @Column
    private String producers;

    @Column
    private boolean winner;
}
