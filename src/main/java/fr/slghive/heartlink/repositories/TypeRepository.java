package fr.slghive.heartlink.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.slghive.heartlink.entities.TypeEntity;

public interface TypeRepository extends CrudRepository<TypeEntity, Integer> {
  Optional<TypeEntity> findByName(String name);

  boolean existsByName(String name);
}
