package com.example.task.repository;

import com.example.task.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findByTitleContainingIgnoreCaseAndYearBetween(String title, Integer yearFrom, Integer yearTo, Pageable pageable);
}
