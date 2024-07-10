package fr.slghive.heartlink.dtos.user;

import java.time.LocalDate;

import fr.slghive.heartlink.dtos.account.AccountPostResponse;
import fr.slghive.heartlink.dtos.address.AddressPostResponse;

public record UserPostResponse(
                AddressPostResponse address,
                AccountPostResponse account,
                LocalDate birthday,
                String firstname,
                String lastname,
                String phone) {

}
