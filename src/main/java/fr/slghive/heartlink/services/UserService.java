package fr.slghive.heartlink.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.slghive.heartlink.dtos.user.UserPostRequest;
import fr.slghive.heartlink.dtos.user.UserPostResponse;
import fr.slghive.heartlink.dtos_mapper.user.UserMapper;
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
        UserEntity user = UserMapper.toEntity(dto);
        Optional<UserEntity> optUser = userRepository.findByAccountEmail(user.getAccount().getEmail());
        if (optUser.isPresent()) {
            throw new DuplicateException("User already exists");
        }

        user = userRepository.save(user);

        return UserMapper.toDto(user);
    }

}