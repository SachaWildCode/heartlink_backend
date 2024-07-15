package fr.slghive.heartlink.repositories;

import fr.slghive.heartlink.entities.OrganizationEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository
  extends JpaRepository<OrganizationEntity, Integer> {
  public Optional<OrganizationEntity> findBySocialName(String socialName);

  public boolean existsBySocialName(String socialName);
}
