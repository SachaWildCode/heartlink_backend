package fr.slghive.heartlink.dtos.donations.donations_post;

import fr.slghive.heartlink.entities.DonationEntity;

public class DonationPostMapper {

    public static DonationPostResponse toDto(DonationEntity donationEntity) {
        return new DonationPostResponse(
                donationEntity.getAmount(),
                donationEntity.getPercentageAttribution(),
                donationEntity.getOrganization().getId());
    }

}