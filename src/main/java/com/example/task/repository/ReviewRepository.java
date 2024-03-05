package com.example.task.repository;

import com.example.task.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByMovieTitleContainingIgnoreCaseAndMovieYearBetween(String title, Integer yearFrom, Integer yearTo, Pageable pageable);
}
