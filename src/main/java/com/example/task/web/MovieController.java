package com.example.task.web;

import com.example.task.model.Movie;
import com.example.task.model.Rating;
import com.example.task.model.Review;
import com.example.task.service.MovieService;
import com.example.task.service.RatingService;
import com.example.task.service.ReviewService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MovieController {
    private final MovieService movieService;
    private final ReviewService reviewService;
    private final RatingService ratingService;

    public MovieController(MovieService movieService, ReviewService reviewService, RatingService ratingService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
        this.ratingService = ratingService;
    }

    @GetMapping("/movies")
    public ResponseEntity<Object> getMovies(@RequestParam(required = false, defaultValue = "0") int page,
                                            @RequestParam(required = false, defaultValue = "6") int size,
                                            @RequestParam(required = false) String sortBy,
                                            @RequestParam(required = false) String title,
                                            @RequestParam(required = false) Integer yearFrom,
                                            @RequestParam(required = false) Integer yearTo) {
        try {
            Sort sort = Sort.by(sortBy).descending();
            return new ResponseEntity<>(movieService.filterMovies(title, yearFrom, yearTo, PageRequest.of(page, size, sort)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/movies")
    public ResponseEntity<Object> addMovie(@RequestBody Movie movie) {
        try {
            return new ResponseEntity<>(movieService.create(movie), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Object> getMovieById(@PathVariable Long id) {
        try {
            Movie movie = movieService.findById(id);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/reviews")
    public ResponseEntity<Object> getReviews(@RequestParam(required = false, defaultValue = "0") int page,
                                             @RequestParam(required = false, defaultValue = "6") int size,
                                             @RequestParam(required = false) String sortBy,
                                             @RequestParam(required = false) String title,
                                             @RequestParam(required = false) Integer yearFrom,
                                             @RequestParam(required = false) Integer yearTo) {
        try {
            System.out.println(sortBy);
            Sort sort = Sort.by(sortBy).descending();
            return new ResponseEntity<>(reviewService.filterReviews(title, yearFrom, yearTo, PageRequest.of(page, size, sort)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/movies/{id}/review")
    public ResponseEntity<Object> addReview(@PathVariable Long id,
                                            @RequestBody Review review) {
        try {
            return new ResponseEntity<>(reviewService.create(id, review), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/movies/{id}/rate")
    public ResponseEntity<Object> addRating(@PathVariable Long id,
                                            @RequestBody Rating rating) {
        try {
            return new ResponseEntity<>(ratingService.create(id, rating), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
