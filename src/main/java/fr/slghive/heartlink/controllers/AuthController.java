package fr.slghive.heartlink.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.slghive.heartlink.dtos.account.account_post.AccountPostRequest;
import fr.slghive.heartlink.dtos.auth.LoginPostResponse;
import fr.slghive.heartlink.dtos.user.user_post.UserPostRequest;
import fr.slghive.heartlink.dtos.user.user_post.UserPostResponse;
import fr.slghive.heartlink.services.AuthService;
import fr.slghive.heartlink.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;

    }

    @PostMapping("/register")
    public ResponseEntity<UserPostResponse> register(@RequestBody @Valid UserPostRequest dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginPostResponse> login(@RequestBody AccountPostRequest dto, HttpServletResponse response) {
        return ResponseEntity.ok(authService.login(dto, response));
    }

}