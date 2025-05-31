package com.example.SpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }


    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody Users user) {

        userRepo.save(user);
        return new ResponseEntity<>("User Registered Successfully", HttpStatus.CREATED);
    }
}
