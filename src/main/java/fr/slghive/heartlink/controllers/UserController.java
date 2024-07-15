package fr.slghive.heartlink.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.slghive.heartlink.dtos.user.user_get.UserGetResponse;
import fr.slghive.heartlink.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserGetResponse> getAuthenticatedUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.getAuthenticatedUser(userDetails));
    }
}
