package fr.slghive.heartlink.dtos.organizations.organization_get;

import fr.slghive.heartlink.dtos.address.address_get.AddressGetResponse;

public record OrganizationGetResponse(
        Integer id,
        String socialName,
        String description,
        String iban,
        AddressGetResponse address) {
}
