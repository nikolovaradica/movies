package com.example.task.service.impl;

import com.example.task.model.Movie;
import com.example.task.model.Review;
import com.example.task.model.exceptions.InvalidMovieIdException;
import com.example.task.repository.MovieRepository;
import com.example.task.repository.ReviewRepository;
import com.example.task.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Page<Review> listReviews(Pageable pageable)  {
        return reviewRepository.findAll(pageable);
    }

    @Override
    public Review create(Long id, Review review) {
        Movie movie = movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
        review.setMovie(movie);
        return reviewRepository.save(review);
    }

    @Override
    public Page<Review> filterReviews(String title, Integer yearFrom, Integer yearTo, Pageable pageable) {
        return reviewRepository.findAllByMovieTitleContainingIgnoreCaseAndMovieYearBetween(title, yearFrom, yearTo, pageable);
    }
}
