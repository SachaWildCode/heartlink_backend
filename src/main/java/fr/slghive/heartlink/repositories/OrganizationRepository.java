package fr.slghive.heartlink.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.slghive.heartlink.entities.OrganizationEntity;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Integer> {

    public List<OrganizationEntity> findBySocialName(String socialName);

    public boolean existsBySocialName(String socialName);

}
