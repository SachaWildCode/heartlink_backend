package fr.slghive.heartlink.dtos.donations.donations_get;

import fr.slghive.heartlink.dtos.organizations.organization_get.OrganizationGetMapper;
import fr.slghive.heartlink.entities.DonationEntity;

public class DonationGetMapper {
    public static DonationGetResponse toDto(DonationEntity donation) {
        return new DonationGetResponse(OrganizationGetMapper.toDto(donation.getOrganization()), donation.getAmount(),
                donation.getPercentageAttribution());
    }

}
