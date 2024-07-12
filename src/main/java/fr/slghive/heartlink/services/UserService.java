package fr.slghive.heartlink.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

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
        UserEntity user = userPostMapper.toEntity(dto);

        Optional<UserEntity> optUser = userRepository.findByAccount_Email(user.getAccount().getEmail());
        if (optUser.isPresent()) {
            throw new DuplicateException("User already exists");
        }

        user = userRepository.save(user);

        return UserPostMapper.toDto(user);
    }

}