package fr.slghive.heartlink.dtos.user.user_get;

import java.time.LocalDate;

import fr.slghive.heartlink.dtos.account.account_post.AccountPostResponse;
import fr.slghive.heartlink.dtos.address.address_post.AddressPostResponse;

public record UserGetRequest(
                Integer id,
                LocalDate birthday,
                String firstname,
                String lastname,
                String phone) {
}
