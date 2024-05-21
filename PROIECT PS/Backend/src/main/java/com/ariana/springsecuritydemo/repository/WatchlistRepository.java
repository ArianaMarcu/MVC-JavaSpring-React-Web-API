package com.ariana.springsecuritydemo.repository;

import com.ariana.springsecuritydemo.model.Movie;
import com.ariana.springsecuritydemo.model.User;
import com.ariana.springsecuritydemo.model.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {
    List<Watchlist> findWatchlistByUser_id(Long user_id);
    Watchlist findByUserAndMovie(User user, Movie movie);
    void deleteByUserAndMovie(User user, Movie movie);
    List<Watchlist> findWatchlistByUser(User user);
}
