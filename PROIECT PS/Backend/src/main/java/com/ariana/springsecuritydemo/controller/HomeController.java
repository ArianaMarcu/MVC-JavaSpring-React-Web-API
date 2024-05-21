package com.ariana.springsecuritydemo.controller;

import com.ariana.springsecuritydemo.model.User;
import com.ariana.springsecuritydemo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(){
        return "This is Home Page";
    }

    @GetMapping("/admin")
    public String admin(){
        return "This is Admin Page";
    }

    @PostMapping("/register")
    public String add(@RequestBody User user){
        user.setRole("USER");
        userService.saveUser(user);
        return "Registered successfully!";
    }
}
