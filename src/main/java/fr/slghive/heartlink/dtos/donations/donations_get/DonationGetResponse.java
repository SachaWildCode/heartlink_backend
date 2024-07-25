package fr.slghive.heartlink.dtos.donations.donations_get;

import fr.slghive.heartlink.dtos.organizations.organization_get.OrganizationGetResponse;

public record DonationGetResponse(
        OrganizationGetResponse organization,
        Integer amount,
        Float percentage) {

}
