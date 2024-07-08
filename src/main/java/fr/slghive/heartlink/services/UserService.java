package fr.slghive.heartlink.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.slghive.heartlink.dtos.user.UserRequestDto;
import fr.slghive.heartlink.dtos.user.UserResponseDto;
import fr.slghive.heartlink.dtos_mapper.user.UserMapper;
import fr.slghive.heartlink.entities.UserEntity;
import fr.slghive.heartlink.exceptions.NotFoundException;
import fr.slghive.heartlink.repositories.AccountRepository;
import fr.slghive.heartlink.repositories.AddressRepository;
import fr.slghive.heartlink.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AccountRepository accountRepository;

    public UserService(UserRepository userRepository, AddressRepository addressRepository,
            AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public UserResponseDto saveUser(UserRequestDto dto) {
        UserEntity user = UserMapper.toUserEntity(dto);
        accountRepository.save(user.getAccount());
        addressRepository.save(user.getAddress());
        userRepository.save(user);
        return UserMapper.toUserPostResponseDto(user);
    }

    public List<UserResponseDto> getUsers() {

        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
        if (users.isEmpty()) {
            throw new NotFoundException("No users found");
        }
        return users.stream()
                .map(UserMapper::toUserPostResponseDto)
                .toList();
    }

    public UserResponseDto getUserById(Integer id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new NotFoundException("User not found");
        }
        return UserMapper.toUserPostResponseDto(user.get());
    }

}
