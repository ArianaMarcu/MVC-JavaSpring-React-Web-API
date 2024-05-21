package com.ariana.springsecuritydemo.notification;

import com.ariana.springsecuritydemo.model.User;
import com.ariana.springsecuritydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {
    private final List<String> subscribedUsers;

    @Autowired
    private UserRepository userRepository;

    public SubscriptionService() {
        this.subscribedUsers = new ArrayList<>();
    }

    public void subscribeUser(String userEmail) {
        if(userRepository.existsUserByEmail(userEmail)){
            if (!subscribedUsers.contains(userEmail)) {
                subscribedUsers.add(userEmail);
            }
        }
        else {
            throw new IllegalArgumentException("User with email " + userEmail + " does not exist in the database.");
        }
    }

    public void unsubscribeUser(String userEmail) {
        subscribedUsers.remove(userEmail);
    }

    public List<String> getSubscribedUsers() {
        List<User> subscribedUserList = userRepository.findBySubscribedTrue();
        return subscribedUserList.stream()
                .map(User::getEmail)
                .collect(Collectors.toList());
    }
}
