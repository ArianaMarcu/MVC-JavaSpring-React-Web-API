package com.ariana.springsecuritydemo.service;
import com.ariana.springsecuritydemo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    public User saveUser(User user);
    User updateUserWithoutPassword(User user);
    public List<User> getAllUsers();
    public User get(Long id);
    public void delete(Long id);
    public boolean existsUserByEmail(String email);
    User getUserByEmail(String email);
    public User updateUser(User user);
}
