package fr.slghive.heartlink.dtos_mapper.user_post;

import fr.slghive.heartlink.dtos.user_post.user.UserPostRequestDto;
import fr.slghive.heartlink.dtos.user_post.user.UserPostResponseDto;
import fr.slghive.heartlink.dtos.user_post.user_account.UserAccountPostResponseDto;
import fr.slghive.heartlink.dtos.user_post.user_address.UserAddressPostResponseDto;
import fr.slghive.heartlink.entities.AccountEntity;
import fr.slghive.heartlink.entities.AddressEntity;
import fr.slghive.heartlink.entities.UserEntity;

public class UserPostMapper {

    public static UserEntity toUserEntity(UserPostRequestDto dto) {
        if (dto.address() == null || dto.account() == null) {
            return null;
        }
        AddressEntity address = new AddressEntity();
        address.setCity(dto.address().city());
        address.setDepartment(dto.address().department());
        address.setStreet(dto.address().street());
        address.setZipCode(dto.address().zipCode());
        address.setRegion(dto.address().region());
        address.setStreetNumber(dto.address().streetNumber());

        AccountEntity account = new AccountEntity();
        account.setPassword(dto.account().password());
        account.setEmail(dto.account().email());

        UserEntity user = new UserEntity();
        user.setAccount(account);
        user.setAddress(address);
        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setPhone(dto.phone());
        user.setBirthday(dto.birthday());
        return user;
    }

    public static UserPostResponseDto toUserPostResponseDto(UserEntity user) {

        UserAddressPostResponseDto userAddressResponseDto = new UserAddressPostResponseDto(
                user.getAddress().getStreet(),
                user.getAddress().getCity(),
                user.getAddress().getZipCode(),
                user.getAddress().getRegion(),
                user.getAddress().getStreetNumber(),
                user.getAddress().getDepartment());

        UserAccountPostResponseDto userAccountResponseDto = new UserAccountPostResponseDto(
                user.getAccount().getEmail());

        return new UserPostResponseDto(userAddressResponseDto,
                userAccountResponseDto,
                user.getFirstname(),
                user.getLastname(),
                user.getBirthday(),
                user.getPhone());
    }
}
