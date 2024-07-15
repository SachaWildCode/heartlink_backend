package fr.slghive.heartlink.dtos.account.account_get;

import fr.slghive.heartlink.entities.AccountEntity;

public class AccountGetMapper {
    public static AccountGetResponse toDto(AccountEntity account) {
        return new AccountGetResponse(
                account.getEmail(),
                account.getRole());
    }
}
