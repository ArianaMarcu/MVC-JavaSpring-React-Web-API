package com.ariana.springsecuritydemo.model;

import javax.persistence.*;

@Entity
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable=false, updatable=false)
    private User user;
    @Column(name = "user_id")
    private Long user_id;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Movie movie;
    @Column(name = "movie_id")
    private Long movie_id;

    public Watchlist() {
    }

    public Watchlist(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Long movie_id) {
        this.movie_id = movie_id;
    }
}
