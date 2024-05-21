package com.ariana.springsecuritydemo.serviceImplementation;
import com.ariana.springsecuritydemo.model.User;
import com.ariana.springsecuritydemo.repository.UserRepository;
import com.ariana.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public ServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setRole(user.getRole());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setSubscribed(user.isSubscribed());
            return this.saveUser(updatedUser);
        } else {
            throw new IllegalArgumentException("User with id " + user.getId() + " not found");
        }
    }

    @Override
    public User updateUserWithoutPassword(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setRole(user.getRole());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setSubscribed(user.isSubscribed());
            return userRepository.save(updatedUser);
        } else {
            throw new IllegalArgumentException("User with id " + user.getId() + " not found");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User get(Long id){
        return userRepository.findById(id).get();
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public boolean existsUserByEmail(String email) {
        System.out.println("Checking existence for email: " + email);
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
