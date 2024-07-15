package fr.slghive.heartlink.services;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.slghive.heartlink.dtos.user.user_get.UserGetMapper;
import fr.slghive.heartlink.dtos.user.user_get.UserGetResponse;
import fr.slghive.heartlink.dtos.user.user_post.UserPostMapper;
import fr.slghive.heartlink.dtos.user.user_post.UserPostRequest;
import fr.slghive.heartlink.dtos.user.user_post.UserPostResponse;
import fr.slghive.heartlink.entities.UserEntity;
import fr.slghive.heartlink.exceptions.DuplicateException;
import fr.slghive.heartlink.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserPostMapper userPostMapper;

    public UserService(UserRepository userRepository, UserPostMapper userPostMapper) {
        this.userRepository = userRepository;
        this.userPostMapper = userPostMapper;
    }

    public UserPostResponse createUser(UserPostRequest dto) {
        if (userRepository.findByAccount_Email(dto.account().email()).isPresent()) {
            throw new DuplicateException("User already exists");
        }
        UserEntity user = userPostMapper.toEntity(dto);
        user = userRepository.save(user);
        return UserPostMapper.toDto(user);
    }

    public UserGetResponse getAuthenticatedUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userRepository.findByAccount_Email(userDetails.getUsername())
                .map(UserGetMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}