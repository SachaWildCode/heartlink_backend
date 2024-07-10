package fr.slghive.heartlink.dtos.user.user_post;

import java.time.LocalDate;

import fr.slghive.heartlink.dtos.account.account_post.AccountPostRequest;
import fr.slghive.heartlink.dtos.address.address_post.AddressPostRequest;

public record UserPostRequest(
                AccountPostRequest account,
                AddressPostRequest address,
                LocalDate birthday,
                String firstname,
                String lastname,
                String phone) {

}
