package com.ariana.springsecuritydemo.model;
import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Review")
    private Long ID_Review;
    private String body;
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id",  insertable = false, updatable = false)
    private Movie movie;

    public Review() {
    }

    public Review(Movie movie, String body) {
        this.movie = movie;
        this.body = body;
    }

    public Long getID_Review() {
        return ID_Review;
    }

    public void setID_Review(Long ID_Review) {
        this.ID_Review = ID_Review;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
