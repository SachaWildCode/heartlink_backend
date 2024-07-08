package fr.slghive.heartlink.dtos.address;

public record AddressResponseDto(
        String street,
        String city,
        String zipCode,
        String department,
        String region,
        String streetNumber) {
}
