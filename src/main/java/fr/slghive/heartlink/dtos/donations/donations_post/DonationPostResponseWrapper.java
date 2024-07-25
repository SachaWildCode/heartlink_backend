package fr.slghive.heartlink.dtos.donations.donations_post;

import java.util.List;

public class DonationPostResponseWrapper {
    private List<DonationPostResponse> successfulDonations;
    private List<String> failedDonations;

    public DonationPostResponseWrapper(List<DonationPostResponse> successfulDonations, List<String> failedDonations) {
        this.successfulDonations = successfulDonations;
        this.failedDonations = failedDonations;
    }

    public List<DonationPostResponse> getSuccessfulDonations() {
        return successfulDonations;
    }

    public List<String> getFailedDonations() {
        return failedDonations;
    }
}