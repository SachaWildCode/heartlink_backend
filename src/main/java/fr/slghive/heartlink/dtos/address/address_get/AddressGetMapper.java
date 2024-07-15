package fr.slghive.heartlink.dtos.address.address_get;

import fr.slghive.heartlink.entities.AddressEntity;

public class AddressGetMapper {
    public static AddressGetResponse toDto(AddressEntity entity) {
        return new AddressGetResponse(
                entity.getStreetNumber(),
                entity.getStreetType(),
                entity.getStreetName(),
                entity.getCity(),
                entity.getRegion(),
                entity.getDepartment(),
                entity.getZipCode());
    }
}
