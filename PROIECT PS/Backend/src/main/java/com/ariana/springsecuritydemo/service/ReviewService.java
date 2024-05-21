package com.ariana.springsecuritydemo.service;

import com.ariana.springsecuritydemo.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface ReviewService {
    public Review addReview(Long id, Review review);
    public List<Review> getAllReviews();
    List<Review> getReviewsForMovie(Long movieId);
    void deleteReview(Long id);
    Review updateReview(Long reviewId, Review updatedReview);
}
