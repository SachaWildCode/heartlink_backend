package fr.slghive.heartlink.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.slghive.heartlink.dtos.user_post.user.UserPostRequestDto;
import fr.slghive.heartlink.dtos.user_post.user.UserPostResponseDto;
import fr.slghive.heartlink.dtos_mapper.user_post.UserPostMapper;
import fr.slghive.heartlink.entities.UserEntity;
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
    public UserPostResponseDto saveUser(UserPostRequestDto dto) {
        UserEntity user = UserPostMapper.toUserEntity(dto);
        accountRepository.save(user.getAccount());
        addressRepository.save(user.getAddress());
        userRepository.save(user);
        return UserPostMapper.toUserPostResponseDto(user);
    }
}
