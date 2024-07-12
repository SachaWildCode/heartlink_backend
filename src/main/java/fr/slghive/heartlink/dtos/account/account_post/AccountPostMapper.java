package fr.slghive.heartlink.dtos.account.account_post;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fr.slghive.heartlink.entities.AccountEntity;

@Component
public class AccountPostMapper {

    private final PasswordEncoder passwordEncoder;

    public AccountPostMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static AccountPostResponse toDto(AccountEntity entity) {
        return new AccountPostResponse(
                entity.getEmail());
    }

    public AccountEntity toEntity(AccountPostRequest dto) {
        AccountEntity account = new AccountEntity();
        account.setEmail(dto.email());
        account.setPassword(this.passwordEncoder.encode(dto.password()));
        System.out.println(this.passwordEncoder.encode(dto.password()));
        return account;
    }
}
