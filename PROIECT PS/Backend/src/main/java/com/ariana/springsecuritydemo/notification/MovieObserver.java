package com.ariana.springsecuritydemo.notification;

import com.ariana.springsecuritydemo.model.MailStructure;
import com.ariana.springsecuritydemo.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieObserver {

    private String nume;

    public MovieObserver() {
    }

    public MovieObserver(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    //    public void update(Movie movie) {
//        String subject = "New Movie Added: " + movie.getTitle();
//        String message = "A new movie has been added to the database: " + movie.getTitle();
//        mailService.sendMail(adminEmail, new MailStructure(subject, message));
//    }
    //void notify(MailStructure mailStructure);
}
