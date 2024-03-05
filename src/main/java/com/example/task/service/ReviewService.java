package com.example.task.service;

import com.example.task.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Page<Review> listReviews(Pageable pageable);
    Review create(Long id, Review review);
    Page<Review> filterReviews(String title, Integer yearFrom, Integer yearTo, Pageable pageable);
}
