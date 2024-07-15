package fr.slghive.heartlink.dtos.organizations.organization_post;

import fr.slghive.heartlink.dtos.address.address_post.AddressPostResponse;

public record OrganizationPostResponse(
        Integer id,
        String socialName,
        String description,
        String iban,
        AddressPostResponse address) {

}
