package fr.slghive.heartlink.dtos.user;

import java.time.LocalDate;

import fr.slghive.heartlink.dtos.account.AccountRequestDto;
import fr.slghive.heartlink.dtos.address.AddressRequestDto;

public record UserRequestDto(
        String firstname,
        String lastname,
        LocalDate birthday,
        String phone,
        AccountRequestDto account,
        AddressRequestDto address) {
}