package fr.slghive.heartlink.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.slghive.heartlink.config.JwtTokenProvider;
import fr.slghive.heartlink.dtos.account.account_post.AccountPostRequest;
import fr.slghive.heartlink.dtos.auth.LoginPostResponse;
import fr.slghive.heartlink.dtos.user.user_get.UserGetMapper;
import fr.slghive.heartlink.dtos.user.user_get.UserGetResponse;
import fr.slghive.heartlink.dtos.user.user_post.UserPostMapper;
import fr.slghive.heartlink.dtos.user.user_post.UserPostRequest;
import fr.slghive.heartlink.dtos.user.user_post.UserPostResponse;
import fr.slghive.heartlink.entities.UserEntity;
import fr.slghive.heartlink.exceptions.DuplicateException;
import fr.slghive.heartlink.repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserPostMapper userPostMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, UserPostMapper userPostMapper, JwtTokenProvider jwtTokenProvider,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userPostMapper = userPostMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public UserPostResponse createUser(UserPostRequest dto) {
        UserEntity user = userPostMapper.toEntity(dto);

        Optional<UserEntity> optUser = userRepository.findByAccount_Email(user.getAccount().getEmail());
        if (optUser.isPresent()) {
            throw new DuplicateException("User already exists");
        }

        user = userRepository.save(user);

        return UserPostMapper.toDto(user);
    }

    public UserGetResponse getAuthenticatedUser(@AuthenticationPrincipal UserDetails userDetails) {
        Optional<UserEntity> user = userRepository.findByAccount_Email(userDetails.getUsername());
        if (user.isPresent()) {
            return UserGetMapper.toDto(user.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    // TODO remove token in response
    public LoginPostResponse login(AccountPostRequest dto, HttpServletResponse response) {
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));
        UserEntity user = (UserEntity) authenticate.getPrincipal();
        Map<String, String> token = jwtTokenProvider.generateToken(user.getAccount().getEmail());
        ResponseCookie cookie = ResponseCookie.from("token", token.get("bearer"))
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(7L * 24 * 60 * 60)
                .build();
        response.addHeader("set-cookie", cookie.toString());
        return new LoginPostResponse(token.get("bearer"));
    }

}