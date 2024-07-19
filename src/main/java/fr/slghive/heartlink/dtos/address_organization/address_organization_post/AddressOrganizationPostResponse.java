package fr.slghive.heartlink.dtos.address_organization.address_organization_post;

public record AddressOrganizationPostResponse(
        Integer id,
        String cplt1,
        String cp,
        String commune,
        String codeInsee,
        String pays) {
}
