package fr.slghive.heartlink.dtos.organizations.organization_get;

import fr.slghive.heartlink.dtos.address.address_get.AddressGetMapper;
import fr.slghive.heartlink.dtos.type.type_get.TypeGetMapper;
import fr.slghive.heartlink.entities.OrganizationEntity;

public class OrganizationGetMapper {
    public static OrganizationGetResponse toDto(OrganizationEntity entity) {
        // System.out.println(entity.getTypes());
        return new OrganizationGetResponse(
                entity.getId(),
                entity.getSocialName(),
                entity.getDescription(),
                entity.getIban(),
                entity.getTypes().stream()
                        .map(TypeGetMapper::toDto)
                        .toList(),
                AddressGetMapper.toDto(entity.getAddress()));
    }
}
