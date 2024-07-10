package fr.slghive.heartlink.dtos.account.account_post;

import fr.slghive.heartlink.entities.AccountEntity;

public class AccountPostMapper {

    public static AccountPostResponse toDto(AccountEntity entity) {
        return new AccountPostResponse(
                entity.getEmail());
    }

    public static AccountEntity toEntity(AccountPostRequest dto) {
        AccountEntity account = new AccountEntity();
        account.setEmail(dto.email());
        account.setPassword(dto.password());
        return account;
    }
}
