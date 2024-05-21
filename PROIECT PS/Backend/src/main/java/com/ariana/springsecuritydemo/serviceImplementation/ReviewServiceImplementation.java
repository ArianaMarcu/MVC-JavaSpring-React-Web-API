package com.ariana.springsecuritydemo.serviceImplementation;

import com.ariana.springsecuritydemo.model.Movie;
import com.ariana.springsecuritydemo.model.Review;
import com.ariana.springsecuritydemo.repository.MovieRepository;
import com.ariana.springsecuritydemo.repository.ReviewRepository;
import com.ariana.springsecuritydemo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReviewServiceImplementation implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ReviewServiceImplementation(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }
    @Override
    public Review addReview(Long id, Review review) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Filmul cu ID-ul specificat nu există"));
        review.setMovie(movie);
        return reviewRepository.save(review);
    }
    @Override
    public List<Review> getAllReviews() {
        return (List<Review>) reviewRepository.findAll();
    }
    @Override
    public List<Review> getReviewsForMovie(Long movieId) {
        return reviewRepository.findByMovieId(movieId);
    }
    @Override
    public void deleteReview(Long ID_Review) {
        reviewRepository.deleteById(ID_Review);
    }

    @Override
    public Review updateReview(Long reviewId, Review updatedReview) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isPresent()) {
            Review existingReview = optionalReview.get();
            existingReview.setBody(updatedReview.getBody());
            return reviewRepository.save(existingReview);
        } else {
            throw new NoSuchElementException("Review not found with ID: " + reviewId);
        }
    }
}
