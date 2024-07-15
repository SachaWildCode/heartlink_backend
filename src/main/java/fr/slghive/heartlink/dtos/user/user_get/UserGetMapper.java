package fr.slghive.heartlink.dtos.user.user_get;

import fr.slghive.heartlink.dtos.account.account_get.AccountGetMapper;
import fr.slghive.heartlink.dtos.address.address_get.AddressGetMapper;
import fr.slghive.heartlink.entities.UserEntity;

public class UserGetMapper {
    public static UserGetResponse toDto(UserEntity user) {
        return new UserGetResponse(
                AddressGetMapper.toDto(user.getAddress()),
                AccountGetMapper.toDto(user.getAccount()),
                user.getBirthday(),
                user.getFirstname(),
                user.getLastname(),
                user.getPhone());

    }
}
