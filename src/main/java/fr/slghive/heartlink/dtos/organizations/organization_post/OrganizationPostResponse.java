package fr.slghive.heartlink.dtos.organizations.organization_post;

import java.util.Set;

import fr.slghive.heartlink.dtos.address.address_post.AddressPostResponse;
import fr.slghive.heartlink.dtos.type.type_post.TypePostResponse;
import jakarta.validation.Valid;

public record OrganizationPostResponse(
        Integer id,
        String socialName,
        String description,
        String iban,
        @Valid Set<TypePostResponse> types,
        AddressPostResponse address) {

}
