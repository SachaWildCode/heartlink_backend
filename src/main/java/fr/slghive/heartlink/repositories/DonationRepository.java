package fr.slghive.heartlink.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.slghive.heartlink.entities.DonationEntity;
import fr.slghive.heartlink.entities.DonationId;
import fr.slghive.heartlink.entities.OrganizationEntity;
import fr.slghive.heartlink.entities.UserEntity;

public interface DonationRepository extends CrudRepository<DonationEntity, DonationId> {
    public List<DonationEntity> findByUser(UserEntity user);

    public List<DonationEntity> findByOrganization(OrganizationEntity organization);

}
