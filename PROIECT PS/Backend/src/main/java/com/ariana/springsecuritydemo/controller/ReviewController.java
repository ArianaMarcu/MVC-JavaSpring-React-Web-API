package com.ariana.springsecuritydemo.controller;

import com.ariana.springsecuritydemo.model.Movie;
import com.ariana.springsecuritydemo.model.Review;
import com.ariana.springsecuritydemo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/admin")
@EnableWebSecurity
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/addReview/{id}")
    public String addReviewToMovie(@PathVariable Long id, @RequestBody Review review) {
        reviewService.addReview(id, review);
        return "Review added successfully!";
    }

    @GetMapping("/getReviews")
    public List<Review> list(){
        return reviewService.getAllReviews();
    }

    @GetMapping("/movie/{movieId}/reviews")
    public List<Review> getReviewsForMovie(@PathVariable Long movieId) {
        return reviewService.getReviewsForMovie(movieId);
    }

    @DeleteMapping("/deleteReview{ID_Review}")
    public String delete(@PathVariable Long ID_Review){
        reviewService.deleteReview(ID_Review);
        return "Deleted Review with id "+ID_Review;
    }

    @PutMapping("/updateReview{ID_Review}")
    public ResponseEntity<Review> updateReview(@PathVariable Long ID_Review, @RequestBody Review updatedReview) {
        try {
            Review updated = reviewService.updateReview(ID_Review, updatedReview);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
