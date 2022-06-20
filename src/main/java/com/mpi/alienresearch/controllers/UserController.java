package com.mpi.alienresearch.controllers;

import java.net.URI;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.service.UserService;
import com.mpi.alienresearch.service.UserServiceImpl;
import com.mpi.alienresearch.state.Credentials;
import com.mpi.alienresearch.state.PersonalInfo;
import com.mpi.alienresearch.state.SingleData;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService = new UserServiceImpl();

    @PostMapping("/login")
    public User Login(@RequestBody Credentials credentials) {
        return userService.login(credentials.getUsername(), credentials.getPassword());
    }

    @PostMapping("/logout")
    public void Logout() {
        userService.logout();
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        long id = userService.add(user);
        if (id > 0) {
            URI uri = URI.create("/users/" + id);
            // System.out.println(uri.toString());
            return ResponseEntity.accepted().location(uri).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") long id) {
        return userService.get(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") long id, @RequestBody SingleData<Long> group) {
        User user = userService.get(id);
        if (user == null) { // dragon not found
            return ResponseEntity.notFound().build();
        }
        user.setGroup(group.getValue());

        userService.update(id, user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/update_group")
    public ResponseEntity<Void> updateUserGroup(@PathVariable("id") long id, @RequestBody User user) {
        User currentUser = userService.get(id);
        if (currentUser == null) { // dragon not found
            return ResponseEntity.notFound().build();
        }
        // dragon exists -> update it
        userService.update(id, user);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/current/update_info")
    public void updateCurrentInfo(@RequestBody PersonalInfo info) {
        userService.updateCurrentInfo(info);
    }
}
