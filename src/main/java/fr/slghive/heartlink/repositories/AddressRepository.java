package fr.slghive.heartlink.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.slghive.heartlink.entities.AddressEntity;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {

    public AddressEntity getAddressById(Integer id);

}
