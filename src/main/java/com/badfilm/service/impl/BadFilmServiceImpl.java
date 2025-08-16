package com.badfilm.service.impl;

import com.badfilm.model.BadFilm;
import com.badfilm.model.dto.IntervalWinnersDto;
import com.badfilm.repository.BadFilmRepository;
import com.badfilm.service.BadFilmService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BadFilmServiceImpl implements BadFilmService {
    private final BadFilmRepository badFilmRepository;
    public static final String COMMA_DELIMITER = ";";

    public BadFilmServiceImpl(BadFilmRepository badFilmRepository) {
        this.badFilmRepository = badFilmRepository;
    }

    @Override
    public List<BadFilm> findAll() {
        return badFilmRepository.findAll();
    }

    @Override
    public void readMovieListCsvAndSaveOnDatabase() {
        List<List<String>> records = readMovieListCsv();

        records.forEach(record -> {
            if (!(record.get(0).equals("year"))) {
                boolean winner = record.size() > 4 && record.get(4).toString().equals("yes") ? true : false;

                BadFilm badFilm = new BadFilm();
                badFilm.setYear(Integer.valueOf(record.get(0)));
                badFilm.setTitle(record.get(1));
                badFilm.setStudios(record.get(2));
                badFilm.setProducers(record.get(3));
                badFilm.setWinner(winner);
                badFilmRepository.save(badFilm);
            }
        });
    }

    @Override
    public Map<String, Object> getIntervalWinners() {
        List<BadFilm> films = badFilmRepository.findAllByWinnerIsTrue();

        Map<String, List<Integer>> winsByProducer = films.stream()
                                                        .flatMap(film -> Arrays.stream(film.getProducers().split(",|and"))
                                                                .map(String::trim)
                                                                .map(producer -> Map.entry(producer, film.getYear())))
                                                        .collect(Collectors.groupingBy(
                                                                Map.Entry::getKey,
                                                                Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                                                        ));

        List<IntervalWinnersDto> intervals = new ArrayList<>();

        winsByProducer.forEach((producer, years) -> {
            Collections.sort(years);

            for (int i = 1; i < years.size(); i++) {
                int intervalWin = years.get(i) - years.get(i - 1);
                int previousWin = years.get(i - 1);
                int followingWin = years.get(i);
                intervals.add(new IntervalWinnersDto(producer, intervalWin, previousWin, followingWin));
            }
        });

        List<IntervalWinnersDto> sortedByMax = intervals.stream()
                                                .sorted((a, b) -> Integer.compare(b.interval, a.interval))
                                                .limit(2)
                                                .toList();

        List<IntervalWinnersDto> sortedByMin = intervals.stream()
                                                .sorted(Comparator.comparingInt(a -> a.interval))
                                                .limit(2)
                                                .toList();

        Map<String, Object> resultWinners = new LinkedHashMap<>();
        resultWinners.put("min", sortedByMin);
        resultWinners.put("max", sortedByMax);

        return resultWinners;
    }

    private List<List<String>> readMovieListCsv() {
        List<List<String>> records = new ArrayList<>();
        Resource resource = new ClassPathResource("movielist.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
            return records;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
