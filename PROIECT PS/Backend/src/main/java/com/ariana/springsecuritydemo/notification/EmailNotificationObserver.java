package com.ariana.springsecuritydemo.notification;

import com.ariana.springsecuritydemo.model.MailStructure;
import com.ariana.springsecuritydemo.model.Movie;
import com.ariana.springsecuritydemo.serviceImplementation.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EmailNotificationObserver {
    @Autowired
    private  MailService mailService;
    private MovieObserver toEmail;

    public EmailNotificationObserver(MailService mailService, MovieObserver toEmail) {
        this.mailService = mailService;
        this.toEmail = toEmail;
    }

    public void update(Movie movie) {
        String subject = "New Movie Added: " + movie.getTitle();
        String message = "A new movie has been added to the website: " + movie.getTitle();
        mailService.sendMail(toEmail.getNume(), new MailStructure(subject, message));
    }

    /*public void notify(MailStructure mailStructure) {
        mailService.sendMail(adminEmail, mailStructure);
    }*/
}
