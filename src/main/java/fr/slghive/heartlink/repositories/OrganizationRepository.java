package fr.slghive.heartlink.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.slghive.heartlink.entities.OrganizationEntity;

public interface OrganizationRepository
    extends JpaRepository<OrganizationEntity, Integer> {

  public Optional<OrganizationEntity> findByName(String name);

  public boolean existsByName(String name);

}
