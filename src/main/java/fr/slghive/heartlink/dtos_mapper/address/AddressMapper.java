package fr.slghive.heartlink.dtos_mapper.address;

import fr.slghive.heartlink.dtos.address.AddressPostRequest;
import fr.slghive.heartlink.dtos.address.AddressPostResponse;
import fr.slghive.heartlink.entities.AddressEntity;

public class AddressMapper {
    public static AddressPostResponse toDto(AddressEntity entity) {
        return new AddressPostResponse(
                entity.getStreetNumber(),
                entity.getStreetType(),
                entity.getStreetName(),
                entity.getCity(),
                entity.getRegion(),
                entity.getDepartment(),
                entity.getZipCode());
    }

    public static AddressEntity toEntity(AddressPostRequest dto) {
        AddressEntity address = new AddressEntity();
        address.setStreetNumber(dto.streetNumber());
        address.setStreetType(dto.streetType());
        address.setStreetName(dto.streetName());
        address.setCity(dto.city());
        address.setRegion(dto.region());
        address.setDepartment(dto.department());
        address.setZipCode(dto.zipCode());
        return address;
    }
}
