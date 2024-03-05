package com.example.task.service.impl;

import com.example.task.model.Movie;
import com.example.task.model.Rating;
import com.example.task.model.exceptions.InvalidMovieIdException;
import com.example.task.repository.MovieRepository;
import com.example.task.repository.RatingRepository;
import com.example.task.service.RatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;

    public RatingServiceImpl(MovieRepository movieRepository, RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating create(Long id, Rating rating) {
        Movie movie = movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
        rating.setMovie(movie);
        return ratingRepository.save(rating);
    }
}
