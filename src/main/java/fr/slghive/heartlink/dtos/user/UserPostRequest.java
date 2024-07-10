package fr.slghive.heartlink.dtos.user;

import java.time.LocalDate;

import fr.slghive.heartlink.dtos.account.AccountPostRequest;
import fr.slghive.heartlink.dtos.address.AddressPostRequest;

public record UserPostRequest(
        AccountPostRequest account,
        AddressPostRequest address,
        LocalDate birthday,
        String firstname,
        String lastname,
        String phone) {

}
