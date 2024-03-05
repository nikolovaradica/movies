package com.example.task.service;

import com.example.task.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {
    Page<Movie> listMovies(Pageable pageable);
    Movie create(Movie movie);
    Movie findById(Long id);
    Page<Movie> filterMovies(String title, Integer yearFrom, Integer yearTo, Pageable pageable);
}
