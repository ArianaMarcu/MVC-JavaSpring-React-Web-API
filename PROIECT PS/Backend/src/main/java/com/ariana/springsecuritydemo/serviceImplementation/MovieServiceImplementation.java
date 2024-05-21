package com.ariana.springsecuritydemo.serviceImplementation;

import com.ariana.springsecuritydemo.model.Movie;
import com.ariana.springsecuritydemo.notification.MovieEventNotifier;
import com.ariana.springsecuritydemo.repository.MovieRepository;
import com.ariana.springsecuritydemo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImplementation implements MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImplementation(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @Override
    public List<Movie> getAllMovies() {
        return (List<Movie>) movieRepository.findAll();
    }
    public Movie singleMovie(Long id){ return movieRepository.findMovieById(id).get(); }
    @Autowired
    private MovieEventNotifier movieEventNotifier;

    // Constructor injection of the MovieEventNotifier
    public MovieServiceImplementation(MovieRepository movieRepository, MovieEventNotifier movieEventNotifier) {
        this.movieRepository = movieRepository;
        this.movieEventNotifier = movieEventNotifier;
    }
    @Override
    public Movie addMovie(Movie movie) {
        //movieEventNotifier.notify(movie);
        return movieRepository.save(movie);
    }
    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
