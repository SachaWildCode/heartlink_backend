package fr.slghive.heartlink.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.slghive.heartlink.entities.AccountEntity;

public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {

}
