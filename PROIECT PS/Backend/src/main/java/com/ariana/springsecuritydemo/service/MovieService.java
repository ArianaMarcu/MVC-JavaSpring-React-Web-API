package com.ariana.springsecuritydemo.service;

import com.ariana.springsecuritydemo.model.Movie;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface MovieService {
        public List<Movie> getAllMovies();
        public Movie singleMovie(Long id);
        public Movie addMovie(Movie movie);
        void deleteMovie(Long id);
}
