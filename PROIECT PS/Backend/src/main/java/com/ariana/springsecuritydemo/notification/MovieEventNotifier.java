package com.ariana.springsecuritydemo.notification;

import com.ariana.springsecuritydemo.model.Movie;
import com.ariana.springsecuritydemo.model.User;
import com.ariana.springsecuritydemo.repository.UserRepository;
import com.ariana.springsecuritydemo.serviceImplementation.MailService;
import org.springframework.stereotype.Component;
import com.ariana.springsecuritydemo.model.MailStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieEventNotifier {
    private final List<EmailNotificationObserver> observers;

    private final UserRepository userRepository;
    private final MailService mailService;

    public MovieEventNotifier(UserRepository userRepository, MailService mailService){
        this.userRepository = userRepository;
        this.mailService = mailService;
        observers = new ArrayList<>();

//        List<String> users_subscribed = userRepository.findBySubscribedTrue()
//                .stream()
//                .map(User::getEmail)
//                .toList();
//
//        for(String email: users_subscribed) {
//            observers.add(new EmailNotificationObserver(mailService, new MovieObserver(email)));
//        }
    }

    public List<String> getSubscribedUsers() {
        List<User> subscribedUserList = userRepository.findBySubscribedTrue();
        return subscribedUserList.stream()
                .map(User::getEmail)
                .collect(Collectors.toList());
    }

    public void addObserver(EmailNotificationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(EmailNotificationObserver observer) {
        observers.remove(observer);
    }

    public void notify(Movie movie) {
        observers.clear();

        List<String> users_subscribed = userRepository.findBySubscribedTrue()
                .stream()
                .map(User::getEmail)
                .toList();

        for(String email: users_subscribed) {
            observers.add(new EmailNotificationObserver(mailService, new MovieObserver(email)));
        }

        for (EmailNotificationObserver observer : observers) {
            observer.update(movie);
        }
    }
}
