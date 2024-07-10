package fr.slghive.heartlink.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.slghive.heartlink.dtos.user.user_get.UserGetRequest;
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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserPostResponse createUser(UserPostRequest dto) {
        UserEntity user = UserPostMapper.toEntity(dto);

        Optional<UserEntity> optUser = userRepository.findByAccountEmail(user.getAccount().getEmail());
        if (optUser.isPresent()) {
            throw new DuplicateException("User already exists");
        }

        user = userRepository.save(user);

        return UserPostMapper.toDto(user);
    }

}