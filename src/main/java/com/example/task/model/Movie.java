package com.example.task.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.text.DecimalFormat;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String genre;
    private int year;
    @OneToMany(mappedBy = "movie")
    @JsonIgnoreProperties("movie")
    private List<Review> reviews;
    @OneToMany(mappedBy = "movie")
    @JsonIgnoreProperties("movie")
    private List<Rating> ratings;

    public Movie() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAverageRating() {
        if (this.ratings == null) {
            return 0;
        }
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        Double avg = this.ratings.stream().mapToDouble(Rating::getRating).summaryStatistics().getAverage();
        return Double.parseDouble(twoDForm.format(avg));
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
