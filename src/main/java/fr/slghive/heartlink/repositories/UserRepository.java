package fr.slghive.heartlink.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.slghive.heartlink.entities.AccountEntity;
import fr.slghive.heartlink.entities.UserEntity;
import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    List<UserEntity> findByAccount(AccountEntity account);
}
