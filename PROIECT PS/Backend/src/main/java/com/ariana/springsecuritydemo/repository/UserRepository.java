package com.ariana.springsecuritydemo.repository;

import com.ariana.springsecuritydemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    boolean existsUserByEmail(String email);
    User findByEmail(String email);
    List<User> findBySubscribedTrue();
}
