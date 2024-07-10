package fr.slghive.heartlink.dtos.address.address_post;

public record AddressPostRequest(
        String streetNumber,
        String streetType,
        String streetName,
        String city,
        String region,
        String department,
        String zipCode) {
}
