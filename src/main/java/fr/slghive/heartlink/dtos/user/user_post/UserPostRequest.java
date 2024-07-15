package fr.slghive.heartlink.dtos.user.user_post;

import java.time.LocalDate;

import fr.slghive.heartlink.dtos.account.account_post.AccountPostRequest;
import fr.slghive.heartlink.dtos.address.address_post.AddressPostRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserPostRequest(
                @Valid AccountPostRequest account,
                @Valid AddressPostRequest address,
                @NotNull LocalDate birthday,
                @NotBlank String firstname,
                @NotBlank String lastname,
                @NotBlank String phone) {

}
