package com.ariana.springsecuritydemo.controller;
import com.ariana.springsecuritydemo.repository.UserRepository;
import com.ariana.springsecuritydemo.service.UserService;
import com.ariana.springsecuritydemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/admin")
@EnableWebSecurity
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public List<User> list(){
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public String add(@RequestBody User user){
        userService.saveUser(user);
        return "New user is added";
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        try {
            User utilizator = userService.get(id);
            return new ResponseEntity<User>(utilizator, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            System.out.println("User not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user,@PathVariable Long id){
        try{
            User existingUser = userService.get(id);
            userService.updateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);

        }catch (NoSuchElementException e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        userService.delete(id);
        return "Deleted User with id "+id;
    }

    @GetMapping("/existsByEmail")
    public ResponseEntity<Boolean> existsUserByEmail(@RequestParam String email) {
        boolean exists = userService.existsUserByEmail(email);
        return ResponseEntity.ok(exists);
    }
}
