package fr.slghive.heartlink.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.slghive.heartlink.entities.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

    public AddressEntity getAddressById(Integer id);

}
