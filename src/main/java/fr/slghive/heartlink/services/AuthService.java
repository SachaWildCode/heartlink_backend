package fr.slghive.heartlink.services;

import java.time.Duration;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import fr.slghive.heartlink.config.JwtTokenProvider;
import fr.slghive.heartlink.dtos.account.account_post.AccountPostRequest;
import fr.slghive.heartlink.dtos.auth.LoginPostResponse;
import fr.slghive.heartlink.entities.UserEntity;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public LoginPostResponse login(AccountPostRequest dto, HttpServletResponse response) {
        System.out.println("ok");
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));
        UserEntity user = (UserEntity) authenticate.getPrincipal();
        Map<String, String> token = jwtTokenProvider.generateToken(user.getAccount().getEmail());
        ResponseCookie cookie = ResponseCookie.from("token", token.get("bearer"))
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(Duration.ofHours(1))
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return new LoginPostResponse(token.get("bearer"));
    }
}