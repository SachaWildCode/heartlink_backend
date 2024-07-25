package fr.slghive.heartlink.dtos.donations.donations_post;

import jakarta.validation.constraints.NotNull;

public record DonationPostResponse(
                @NotNull Integer amount,
                @NotNull Float percentageAttribution,
                @NotNull Integer organizationId) {
}
