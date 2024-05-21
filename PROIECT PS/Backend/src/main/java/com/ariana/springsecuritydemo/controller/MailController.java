package com.ariana.springsecuritydemo.controller;

import com.ariana.springsecuritydemo.model.MailStructure;
import com.ariana.springsecuritydemo.model.User;
import com.ariana.springsecuritydemo.notification.EmailNotificationObserver;
import com.ariana.springsecuritydemo.notification.MovieEventNotifier;
import com.ariana.springsecuritydemo.notification.MovieObserver;
import com.ariana.springsecuritydemo.notification.SubscriptionService;
import com.ariana.springsecuritydemo.repository.UserRepository;
import com.ariana.springsecuritydemo.service.UserService;
import com.ariana.springsecuritydemo.serviceImplementation.MailService;
import com.ariana.springsecuritydemo.serviceImplementation.ServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;
    private final UserService userService;
    private final MovieEventNotifier movieEventNotifier;
    private final SubscriptionService subscriptionService;
    private final JavaMailSender mailSender;

    @Autowired
    public MailController(MailService mailService, UserService userService, MovieEventNotifier movieEventNotifier, SubscriptionService subscriptionService, JavaMailSender mailSender) {
        this.mailService = mailService;
        this.userService = userService;
        this.movieEventNotifier = movieEventNotifier;
        this.subscriptionService = subscriptionService;
        this.mailSender = mailSender;
    }

    @PostMapping("/send/{mail}")
    public String sendMail(@PathVariable String mail, @RequestBody MailStructure mailStructure){
        mailService.sendMail(mail, mailStructure);
        return "Successfully sent the mail!";
    }

    @PostMapping("/send")
    public String sendMailToSubscribers(@RequestBody MailStructure mailStructure) {
        List<String> subscribers = subscriptionService.getSubscribedUsers();
        for (String subscriber : subscribers) {
            mailService.sendMail(subscriber, mailStructure);
        }
        return "Successfully sent mails to all subscribers!";
    }

    @PostMapping("/subscribe")
    public String subscribeToMovieNotifications(@RequestParam String email) {
        if (userService.existsUserByEmail(email)) {
            EmailNotificationObserver observer = new EmailNotificationObserver(new MailService(mailSender), new MovieObserver(email));
            movieEventNotifier.addObserver(observer);
            //movieEventNotifier.notify();
            subscriptionService.subscribeUser(email);
            User user = userService.getUserByEmail(email);
            user.setSubscribed(true);
            userService.updateUserWithoutPassword(user);
            return "Successfully subscribed to movie notifications!";
        } else {
            return "User with email " + email + " does not exist in the database. Subscription failed.";
        }
    }

    /*public MovieEventNotifier getService(){
        List<String> subscribers = getSubscribedUsers();
        for (String subscriber : subscribers) {
            movieEventNotifier.addObserver(new EmailNotificationObserver(new MailService(mailSender), new MovieObserver(subscriber)));
        }
        return movieEventNotifier;
    }*/

    @GetMapping("/subscribers")
    public List<String> getSubscribedUsers() {
        return subscriptionService.getSubscribedUsers();
    }

    @PostMapping("/unsubscribe")
    public String unsubscribeFromMovieNotifications(@RequestParam String email) {
        if (userService.existsUserByEmail(email)) {
            EmailNotificationObserver observer = new EmailNotificationObserver(new MailService(mailSender), new MovieObserver(email));
            movieEventNotifier.removeObserver(observer);
            subscriptionService.unsubscribeUser(email);
            User user = userService.getUserByEmail(email);
            user.setSubscribed(false);
            userService.updateUserWithoutPassword(user);
            return "You are UNSUBSCRIBED!";
        } else {
            return "User with email " + email + " does not exist in the database. Couldn't unsubscribe.";
        }
    }
}
