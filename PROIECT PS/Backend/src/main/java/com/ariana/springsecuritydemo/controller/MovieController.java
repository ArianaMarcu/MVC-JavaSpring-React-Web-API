package com.ariana.springsecuritydemo.controller;

import com.ariana.springsecuritydemo.model.Movie;
import com.ariana.springsecuritydemo.notification.MovieEventNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import com.ariana.springsecuritydemo.service.MovieService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@EnableWebSecurity
public class MovieController {

    private final MovieService movieService;
    private final MovieEventNotifier movieEventNotifier;

    @Autowired
    public MovieController(MovieService movieService, MovieEventNotifier movieEventNotifier){
        this.movieService = movieService;
        this.movieEventNotifier = movieEventNotifier;
    }

    @GetMapping("/getMovies")
    public List<Movie> list(){
        return movieService.getAllMovies();
    }

    @GetMapping("/movie{id}")
    public ResponseEntity<Movie> get(@PathVariable Long id) {
        try {
            Movie movie = movieService.singleMovie(id);
            return new ResponseEntity<Movie>(movie, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            System.out.println("Movie not found");
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addMovie")
    public String add(@RequestBody Movie movie){
        movieService.addMovie(movie);
        movieEventNotifier.notify(movie);
        return "New movie is added";
    }

    @PutMapping("/updateMovie{id}")
    public ResponseEntity<Movie> update(@RequestBody Movie movie,@PathVariable Long id){
        try{
            Movie existingMovie = movieService.singleMovie(id);
            movieService.addMovie(movie);
            return new ResponseEntity<>(HttpStatus.OK);

        }catch (NoSuchElementException e){
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteMovie{id}")
    public String delete(@PathVariable Long id){
        movieService.deleteMovie(id);
        return "Deleted Movie with id "+id;
    }
}
