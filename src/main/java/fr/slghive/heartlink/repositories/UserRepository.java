package fr.slghive.heartlink.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.slghive.heartlink.entities.AccountEntity;
import fr.slghive.heartlink.entities.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    List<UserEntity> findByAccount(AccountEntity account);

    Optional<UserEntity> findByAccount_Email(String email);

}
