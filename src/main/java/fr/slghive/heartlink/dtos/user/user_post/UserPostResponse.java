package fr.slghive.heartlink.dtos.user.user_post;

import java.time.LocalDate;

import fr.slghive.heartlink.dtos.account.account_post.AccountPostResponse;
import fr.slghive.heartlink.dtos.address.address_post.AddressPostResponse;

public record UserPostResponse(
        AddressPostResponse address,
        AccountPostResponse account,
        LocalDate birthday,
        String firstname,
        String lastname,
        String phone) {

}
