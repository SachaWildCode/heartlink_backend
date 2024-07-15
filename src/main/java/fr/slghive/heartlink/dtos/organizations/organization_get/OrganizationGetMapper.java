package fr.slghive.heartlink.dtos.organizations.organization_get;

import fr.slghive.heartlink.dtos.address.address_get.AddressGetMapper;
import fr.slghive.heartlink.entities.OrganizationEntity;

public class OrganizationGetMapper {
    public static OrganizationGetResponse toDto(OrganizationEntity entity) {
        return new OrganizationGetResponse(
                entity.getId(),
                entity.getSocialName(),
                entity.getDescription(),
                entity.getIban(),
                AddressGetMapper.toDto(entity.getAddress()));
    }
}
