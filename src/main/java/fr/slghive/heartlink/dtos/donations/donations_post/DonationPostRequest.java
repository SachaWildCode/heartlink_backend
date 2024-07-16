package fr.slghive.heartlink.dtos.donations.donations_post;

import jakarta.validation.constraints.NotNull;

public record DonationPostRequest(
        @NotNull float percentageAttribution,
        @NotNull Integer organizationId) {
}