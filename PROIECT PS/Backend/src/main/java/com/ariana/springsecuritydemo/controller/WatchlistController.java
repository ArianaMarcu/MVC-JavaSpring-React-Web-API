package com.ariana.springsecuritydemo.controller;

import com.ariana.springsecuritydemo.model.Movie;
import com.ariana.springsecuritydemo.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
@EnableWebSecurity
public class WatchlistController {
    private final WatchlistService watchlistService;

    @Autowired
    public WatchlistController(WatchlistService watchlistService) {
        this.watchlistService = watchlistService;
    }

    @PostMapping("/{userId}/add/{movieId}")
    public ResponseEntity<String> addToWatchlist(@PathVariable Long userId, @PathVariable Long movieId) {
        try {
            watchlistService.addToWatchlist(userId, movieId);
            return new ResponseEntity<>("Movie added to watchlist", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}/remove/{movieId}")
    public ResponseEntity<String> removeFromWatchlist(@PathVariable Long userId, @PathVariable Long movieId) {
        try {
            watchlistService.removeFromWatchlist(userId, movieId);
            return new ResponseEntity<>("Movie removed from watchlist", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/myWatchlist")
    public ResponseEntity<List<Movie>> getWatchlistForAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            List<Movie> watchlist = watchlistService.getWatchlistForUsername(username);
            return new ResponseEntity<>(watchlist, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/watchlist{userId}")
    public ResponseEntity<List<Movie>> getWatchlistForUser(@PathVariable Long userId) {
        try {
            List<Movie> watchlist = watchlistService.getWatchlistForUser(userId);
            return new ResponseEntity<>(watchlist, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
