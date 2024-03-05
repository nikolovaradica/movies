package com.example.task.service.impl;

import com.example.task.model.Movie;
import com.example.task.model.exceptions.InvalidMovieIdException;
import com.example.task.repository.MovieRepository;
import com.example.task.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Page<Movie> listMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Override
    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
    }

    @Override
    public Page<Movie> filterMovies(String title, Integer yearFrom, Integer yearTo, Pageable pageable) {
        return movieRepository.findByTitleContainingIgnoreCaseAndYearBetween(title, yearFrom, yearTo, pageable);
    }
}
