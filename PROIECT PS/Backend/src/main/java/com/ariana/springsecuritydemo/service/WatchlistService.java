package com.ariana.springsecuritydemo.service;

import com.ariana.springsecuritydemo.model.Movie;
import com.ariana.springsecuritydemo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WatchlistService {
    public void addToWatchlist(Long user_id, Long movie_id);
    public void removeFromWatchlist(Long user_id, Long movie_id);
    public List<Movie> getWatchlistForUser(Long user_id);
    List<Movie> getWatchlistForUsername(String username);
}
