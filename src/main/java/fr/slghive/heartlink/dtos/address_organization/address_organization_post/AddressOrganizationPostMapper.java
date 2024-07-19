package fr.slghive.heartlink.dtos.address_organization.address_organization_post;

import fr.slghive.heartlink.entities.AddressOrganization;

public class AddressOrganizationPostMapper {

    public static AddressOrganization toEntity(AddressOrganizationPostRequest dto) {

        AddressOrganization addressGestion = new AddressOrganization();
        addressGestion.setCplt1(dto.cplt_1());
        addressGestion.setCp(dto.cp());
        addressGestion.setCommune(dto.commune());
        addressGestion.setCodeInsee(dto.code_insee());
        addressGestion.setPays(dto.pays());
        return addressGestion;
    }

    public static AddressOrganizationPostResponse toDto(AddressOrganization entity) {
        return new AddressOrganizationPostResponse(entity.getId(), entity.getCplt1(), entity.getCp(),
                entity.getCommune(),
                entity.getCodeInsee(), entity.getPays());
    }

}