package fr.slghive.heartlink.dtos.organizations.organization_get;

import java.util.List;

import fr.slghive.heartlink.dtos.address.address_get.AddressGetResponse;
import fr.slghive.heartlink.dtos.type.type_get.TypeGetResponse;

public record OrganizationGetResponse(
        Integer id,
        String socialName,
        String description,
        String iban,
        List<TypeGetResponse> types,
        AddressGetResponse address) {
}
