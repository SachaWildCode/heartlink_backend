package fr.slghive.heartlink.dtos_mapper.user;

import fr.slghive.heartlink.dtos.user.UserPostRequest;
import fr.slghive.heartlink.dtos.user.UserPostResponse;
import fr.slghive.heartlink.dtos_mapper.account.AccountMapper;
import fr.slghive.heartlink.dtos_mapper.address.AddressMapper;
import fr.slghive.heartlink.entities.UserEntity;

public class UserMapper {

    public static UserPostResponse toDto(UserEntity entity) {
        return new UserPostResponse(
                AddressMapper.toDto(entity.getAddress()),
                AccountMapper.toDto(entity.getAccount()),
                entity.getBirthday(),
                entity.getFirstname(),
                entity.getLastname(),
                entity.getPhone());
    }

    public static UserEntity toEntity(UserPostRequest dto) {
        UserEntity user = new UserEntity();
        user.setAccount(AccountMapper.toEntity(dto.account()));
        user.setAddress(AddressMapper.toEntity(dto.address()));
        user.setBirthday(dto.birthday());
        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setPhone(dto.phone());
        return user;
    }
}