package fr.slghive.heartlink.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(@AuthenticationPrincipal UserDetails userDetails,
            HttpServletResponse response) {
        authService.logout(response);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "Logout successful");
        return ResponseEntity.ok(responseBody);
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