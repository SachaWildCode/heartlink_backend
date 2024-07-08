package fr.slghive.heartlink.dtos.user_post.user;

import java.time.LocalDate;

import fr.slghive.heartlink.dtos.user_post.user_account.UserAccountPostResponseDto;
import fr.slghive.heartlink.dtos.user_post.user_address.UserAddressPostResponseDto;

public record UserPostResponseDto(
        UserAddressPostResponseDto address,
        UserAccountPostResponseDto account,
        String firstname,
        String lastname,
        LocalDate birthday,
        String phone) {
}
