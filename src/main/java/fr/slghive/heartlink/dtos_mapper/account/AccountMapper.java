package fr.slghive.heartlink.dtos_mapper.account;

import fr.slghive.heartlink.dtos.account.AccountPostRequest;
import fr.slghive.heartlink.dtos.account.AccountPostResponse;
import fr.slghive.heartlink.entities.AccountEntity;

public class AccountMapper {

    public static AccountPostResponse toDto(AccountEntity entity) {
        return new AccountPostResponse(
                entity.getEmail(),
                entity.getPassword());
    }

    public static AccountEntity toEntity(AccountPostRequest dto) {
        AccountEntity account = new AccountEntity();
        account.setEmail(dto.email());
        account.setPassword(dto.password());
        return account;
    }
}
