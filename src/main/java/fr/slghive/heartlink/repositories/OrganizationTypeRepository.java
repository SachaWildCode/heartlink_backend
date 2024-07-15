package fr.slghive.heartlink.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.slghive.heartlink.entities.OrganizationEntity;
import fr.slghive.heartlink.entities.OrganizationType;
import fr.slghive.heartlink.entities.OrganizationTypeId;

public interface OrganizationTypeRepository extends CrudRepository<OrganizationType, OrganizationTypeId> {

    public List<OrganizationEntity> findOrganizationEntitiesByType(OrganizationType type);

    public OrganizationEntity
}
