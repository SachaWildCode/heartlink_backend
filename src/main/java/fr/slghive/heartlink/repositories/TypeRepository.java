package fr.slghive.heartlink.repositories;

import fr.slghive.heartlink.entities.TypeEntity;
import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<TypeEntity, Integer> {
  boolean existsByName(String name);
}
