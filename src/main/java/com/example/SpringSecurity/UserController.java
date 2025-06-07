package com.example.SpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService service;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return service.verify(user);
    }


    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return new ResponseEntity<>("User Registered Successfully", HttpStatus.CREATED);
    }
}
