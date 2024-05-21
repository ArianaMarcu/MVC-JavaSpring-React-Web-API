package com.ariana.springsecuritydemo.serviceImplementation;

import com.ariana.springsecuritydemo.model.Movie;
import com.ariana.springsecuritydemo.model.Review;
import com.ariana.springsecuritydemo.model.User;
import com.ariana.springsecuritydemo.model.Watchlist;
import com.ariana.springsecuritydemo.repository.MovieRepository;
import com.ariana.springsecuritydemo.repository.UserRepository;
import com.ariana.springsecuritydemo.repository.WatchlistRepository;
import com.ariana.springsecuritydemo.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchlistServiceImplementation implements WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Autowired
    public WatchlistServiceImplementation(WatchlistRepository watchlistRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.watchlistRepository = watchlistRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public void addToWatchlist(Long userId, Long movieId) {
        User user = userRepository.findById(userId).orElse(null);
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (user != null && movie != null) {
            Watchlist existingWatchlistItem = watchlistRepository.findByUserAndMovie(user, movie);
            if (existingWatchlistItem == null) {
                Watchlist newWatchlistItem = new Watchlist(user, movie);
                watchlistRepository.save(newWatchlistItem);
                newWatchlistItem.setMovie_id(movie.getId());
                newWatchlistItem.setUser_id(user.getId());
            } else {
                throw new RuntimeException("Movie already exists in watchlist for the user");
            }
        } else {
            throw new RuntimeException("User or Movie not found");
        }
    }
    @Transactional
    public void removeFromWatchlist(Long userId, Long movieId) {
        User user = userRepository.findById(userId).orElse(null);
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (user != null && movie != null) {
            Watchlist watchlistItem = watchlistRepository.findByUserAndMovie(user, movie);
            if (watchlistItem != null) {
                watchlistRepository.delete(watchlistItem);
            } else {
                throw new RuntimeException("Movie not found in watchlist for the user");
            }
        } else {
            throw new RuntimeException("User or Movie not found");
        }
    }
    @Override
    public List<Movie> getWatchlistForUser(Long user_id) {
        List<Watchlist> watchlistItems = watchlistRepository.findWatchlistByUser_id(user_id);
        return watchlistItems.stream().map(Watchlist::getMovie).collect(Collectors.toList());
    }

    public List<Movie> getWatchlistForUsername(String username) {
        User user = userRepository.findByUsername(username);
        List<Watchlist> watchlistItems = watchlistRepository.findWatchlistByUser(user);
        if (user != null) {
            return watchlistItems.stream().map(Watchlist::getMovie).collect(Collectors.toList());
        } else {
            return null;
        }
    }

}
