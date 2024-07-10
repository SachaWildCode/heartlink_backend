package fr.slghive.heartlink.dtos.address;

public record AddressPostResponse(
                String streetNumber,
                String streetType,
                String streetName,
                String city,
                String region,
                String department,
                String zipCode) {
}
