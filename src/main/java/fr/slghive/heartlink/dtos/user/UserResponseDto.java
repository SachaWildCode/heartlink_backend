package fr.slghive.heartlink.dtos.user;

import java.time.LocalDate;

import fr.slghive.heartlink.dtos.account.AccountResponseDto;
import fr.slghive.heartlink.dtos.address.AddressResponseDto;

public record UserResponseDto(
                AddressResponseDto address,
                AccountResponseDto account,
                String firstname,
                String lastname,
                LocalDate birthday,
                String phone) {
}
