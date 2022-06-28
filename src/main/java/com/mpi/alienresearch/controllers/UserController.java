package com.mpi.alienresearch.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mpi.alienresearch.model.Credentials;
import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.service.UserService;
import com.mpi.alienresearch.service.UserServiceImpl;
import com.mpi.alienresearch.state.PersonalInfo;
import com.mpi.alienresearch.state.SingleData;
import com.mpi.alienresearch.state.State;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public User Login(@RequestBody Credentials credentials) {
        return userService.login(credentials.getUsername(), credentials.getPassword());
    }

    @PostMapping("/logout")
    public void Logout(@RequestBody SingleData<String> credentials) {
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

    @GetMapping
    public List<User> getUsers() {
        return userService.getPage(1l,1l,null);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") long id) {
        return userService.get(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        userService.update(id, user);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/update_group")
    public ResponseEntity<Void> updateUserGroup(@PathVariable("id") long id,
                @RequestParam(name = "group") Optional<Long> groupId) {
        
        userService.updateGroup(id, groupId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/current")
    public User getCurrentUser() {
        return userService.get(State.getCurrentUser().getId());
    }

    @PutMapping("/current")
    public void updateCurrentInfo(@RequestBody User user) {
        userService.update(State.getCurrentUser().getId(), user);
    }

    @PostMapping("/set_password")
    public void updatePassword(@RequestBody String password) {
        userService.setPassword(password);
    }
}
