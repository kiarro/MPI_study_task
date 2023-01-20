package com.mpi.alienresearch.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mpi.alienresearch.model.User;
import com.mpi.alienresearch.service.UserService;
import com.mpi.alienresearch.state.PersonalInfo;
import com.mpi.alienresearch.state.SingleData;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if (userService.saveUser(user)) {
            return ResponseEntity.ok().build();
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
        user.setId(id);
        userService.updateUser(user);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/update_group")
    public ResponseEntity<Void> updateUserGroup(@PathVariable("id") long id,
                @RequestParam(name = "group") Optional<String> group) {
        
        userService.updateGroup(id, group);
        return ResponseEntity.noContent().build();
    }

    // @GetMapping("/current")
    // public User getCurrentUser(Authentication auth) {
    //     return userService.get(userService.loadUserByUsername(auth.getName()).getId());
    // }

    // @PutMapping("/current")
    // public void updateCurrentInfo(@RequestBody User user, Authentication auth) {
    //     user.setId(userService.loadUserByUsername(auth.getName()).getId());
    //     userService.updateUser(user);
    // }
}
