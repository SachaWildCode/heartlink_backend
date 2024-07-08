package fr.slghive.heartlink.dtos_mapper.user;

import fr.slghive.heartlink.dtos.account.AccountResponseDto;
import fr.slghive.heartlink.dtos.address.AddressResponseDto;
import fr.slghive.heartlink.dtos.user.UserRequestDto;
import fr.slghive.heartlink.dtos.user.UserResponseDto;
import fr.slghive.heartlink.entities.AccountEntity;
import fr.slghive.heartlink.entities.AddressEntity;
import fr.slghive.heartlink.entities.UserEntity;

public class UserMapper {

    public static UserEntity toUserEntity(UserRequestDto dto) {
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

    public static UserResponseDto toUserPostResponseDto(UserEntity user) {

        AddressResponseDto userAddressResponseDto = new AddressResponseDto(
                user.getAddress().getStreet(),
                user.getAddress().getCity(),
                user.getAddress().getZipCode(),
                user.getAddress().getRegion(),
                user.getAddress().getStreetNumber(),
                user.getAddress().getDepartment());

        AccountResponseDto userAccountResponseDto = new AccountResponseDto(
                user.getAccount().getEmail());

        return new UserResponseDto(userAddressResponseDto,
                userAccountResponseDto,
                user.getFirstname(),
                user.getLastname(),
                user.getBirthday(),
                user.getPhone());
    }
}
