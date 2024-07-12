package fr.slghive.heartlink.dtos.user.user_post;

import org.springframework.stereotype.Component;

import fr.slghive.heartlink.dtos.account.account_post.AccountPostMapper;
import fr.slghive.heartlink.dtos.address.address_post.AddressPostMapper;
import fr.slghive.heartlink.entities.UserEntity;

@Component
public class UserPostMapper {

    private final AccountPostMapper accountPostMapper;

    public UserPostMapper(AccountPostMapper accountPostMapper) {
        this.accountPostMapper = accountPostMapper;
    }

    public static UserPostResponse toDto(UserEntity entity) {

        return new UserPostResponse(
                AddressPostMapper.toDto(entity.getAddress()),
                AccountPostMapper.toDto(entity.getAccount()),
                entity.getBirthday(),
                entity.getFirstname(),
                entity.getLastname(),
                entity.getPhone());
    }

    public UserEntity toEntity(UserPostRequest dto) {
        UserEntity user = new UserEntity();
        user.setAccount(this.accountPostMapper.toEntity(dto.account()));
        user.setAddress(AddressPostMapper.toEntity(dto.address()));
        user.setBirthday(dto.birthday());
        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setPhone(dto.phone());
        return user;
    }
}