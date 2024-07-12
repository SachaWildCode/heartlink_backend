package fr.slghive.heartlink.controllers;

import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.slghive.heartlink.config.JwtTokenProvider;
import fr.slghive.heartlink.dtos.account.account_post.AccountPostRequest;
import fr.slghive.heartlink.dtos.account.account_post.AccountPostResponse;
import fr.slghive.heartlink.dtos.user.user_post.UserPostRequest;
import fr.slghive.heartlink.dtos.user.user_post.UserPostResponse;
import fr.slghive.heartlink.entities.UserEntity;
import fr.slghive.heartlink.exceptions.DuplicateException;
import fr.slghive.heartlink.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<UserPostResponse> register(@RequestBody UserPostRequest dto) {
        try {
            return ResponseEntity.ok(userService.createUser(dto));
        } catch (DuplicateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
    }

    @PostMapping("/login")
    public ResponseEntity<AccountPostResponse> login(@Valid @RequestBody AccountPostRequest dto,
            HttpServletResponse response) {
        try {
            final Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));
            if (authenticate.isAuthenticated()) {
                UserEntity user = (UserEntity) authenticate.getPrincipal();
                Map<String, String> token = jwtTokenProvider.generateToken(user.getAccount().getEmail());
                ResponseCookie cookie = ResponseCookie.from("token", token.get("bearer"))
                        .httpOnly(true)
                        .secure(true)
                        .path("/")
                        .maxAge(7L * 24 * 60 * 60)
                        .build();

                response.addHeader("set-cookie", cookie.toString());
                return ResponseEntity.ok().build();
            }

        } catch (BadCredentialsException b) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}