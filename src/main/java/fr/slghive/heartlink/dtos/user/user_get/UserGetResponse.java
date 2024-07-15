package fr.slghive.heartlink.dtos.user.user_get;

import java.time.LocalDate;

import fr.slghive.heartlink.dtos.account.account_get.AccountGetResponse;
import fr.slghive.heartlink.dtos.address.address_get.AddressGetResponse;

public record UserGetResponse(
                AddressGetResponse address,
                AccountGetResponse account,
                LocalDate birthday,
                String firstname,
                String lastname,
                String phone) {
}
