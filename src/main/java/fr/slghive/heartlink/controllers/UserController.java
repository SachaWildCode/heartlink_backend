package fr.slghive.heartlink.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import fr.slghive.heartlink.dtos.user_post.user.UserPostRequestDto;
import fr.slghive.heartlink.dtos.user_post.user.UserPostResponseDto;
import fr.slghive.heartlink.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserPostResponseDto> saveUser(@RequestBody UserPostRequestDto dto) {
        return ResponseEntity.ok(userService.saveUser(dto));
    }
}
