package fr.slghive.heartlink.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.slghive.heartlink.entities.OrganizationEntity;

public interface OrganizationRepository
    extends JpaRepository<OrganizationEntity, Integer> {

  public Optional<OrganizationEntity> findByName(String name);

  public boolean existsByName(String name);

  public Page<OrganizationEntity> findAll(Pageable pageable);

  @Query("SELECT o FROM OrganizationEntity o WHERE o.id NOT IN (SELECT d.organization.id FROM DonationEntity d WHERE d.user.account.email = :username)")
  Page<OrganizationEntity> findAllByUserNotDonated(@Param("username") String username, Pageable pageable);
}
