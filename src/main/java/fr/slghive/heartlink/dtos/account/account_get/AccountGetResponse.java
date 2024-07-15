package fr.slghive.heartlink.dtos.account.account_get;

import fr.slghive.heartlink.enums.RoleEnum;

public record AccountGetResponse(
                String email,
                RoleEnum role) {

}
