package fr.slghive.heartlink.dtos.user_post.user;

import java.time.LocalDate;

import fr.slghive.heartlink.dtos.user_post.user_account.UserAccountPostRequestDto;
import fr.slghive.heartlink.dtos.user_post.user_address.UserAddressPostRequestDto;

public record UserPostRequestDto(
                String firstname,
                String lastname,
                LocalDate birthday,
                String phone,
                UserAccountPostRequestDto account,
                UserAddressPostRequestDto address) {
}