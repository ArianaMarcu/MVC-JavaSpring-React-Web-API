package com.ariana.springsecuritydemo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imdb_Id;
    private String title;
    private String release_Date;
    private String trailer_Link;
    private String genres;
    private String poster;
    private String backdrops;

    public Movie(Long id, String imdbId, String title, String releaseDate, String trailerLink, String genres, String poster, String backdrops) {
        this.id = id;
        this.imdb_Id = imdbId;
        this.title = title;
        this.release_Date = releaseDate;
        this.trailer_Link = trailerLink;
        this.genres = genres;
        this.poster = poster;
        this.backdrops = backdrops;
    }

    public Movie() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImdb_Id() {
        return imdb_Id;
    }

    public void setImdb_Id(String imdb_Id) {
        this.imdb_Id = imdb_Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_Date() {
        return release_Date;
    }

    public void setRelease_Date(String release_Date) {
        this.release_Date = release_Date;
    }

    public String getTrailer_Link() {
        return trailer_Link;
    }

    public void setTrailer_Link(String trailer_Link) {
        this.trailer_Link = trailer_Link;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(String backdrops) {
        this.backdrops = backdrops;
    }
}
