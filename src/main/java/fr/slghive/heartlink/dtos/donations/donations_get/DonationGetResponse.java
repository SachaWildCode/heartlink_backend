package fr.slghive.heartlink.dtos.donations.donations_get;

public record DonationGetResponse(
                Long id,
                String name,
                String description,
                String amount,
                String currency,
                String date,
                String status) {

}
