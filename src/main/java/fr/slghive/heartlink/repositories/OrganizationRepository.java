package fr.slghive.heartlink.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.slghive.heartlink.entities.OrganizationEntity;

public interface OrganizationRepository extends CrudRepository<OrganizationEntity, Integer> {

    public List<OrganizationEntity> findBySocialName(String socialName);

}
