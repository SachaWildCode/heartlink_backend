package fr.slghive.heartlink.dtos.address_organization.address_organization_get;

import fr.slghive.heartlink.entities.AddressOrganization;

public class AddressOrganizationGetMapper {
    public static AddressOrganizationGetResponse toDto(AddressOrganization entity) {
        return new AddressOrganizationGetResponse(entity.getId(), entity.getCplt1(), entity.getCp(),
                entity.getCommune(), entity.getCodeInsee(), entity.getPays());
    }
}