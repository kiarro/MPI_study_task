package com.mpi.alienresearch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpi.alienresearch.model.Credentials;
import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {
    
    private final UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> login(@RequestBody Credentials credentials) {
        try {
            User u = userService.loadUserByUsername(credentials.getUsername());
            boolean b = bCryptPasswordEncoder.matches(credentials.getPassword(), u.getPassword());
            if (b) {
                return ResponseEntity.ok(u);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
