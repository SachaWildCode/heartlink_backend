package fr.slghive.heartlink.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.slghive.heartlink.entities.TypeEntity;

public interface TypeRepository extends CrudRepository<TypeEntity, Integer> {
    boolean existsByName(String name);
}