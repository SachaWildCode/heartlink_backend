package fr.slghive.heartlink.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.http.ResponseEntity;

import fr.slghive.heartlink.exceptions.DuplicateException;
import fr.slghive.heartlink.services.UserService;
import fr.slghive.heartlink.dtos.user.user_post.UserPostRequest;
import fr.slghive.heartlink.dtos.user.user_post.UserPostResponse;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserPostResponse> saveUser(@RequestBody UserPostRequest dto) {
        try {
            return ResponseEntity.ok(userService.createUser(dto));
        } catch (DuplicateException e) {
            throw new ResponseStatusException(e.getStatus(), e.getMessage());
        }
    }

}
