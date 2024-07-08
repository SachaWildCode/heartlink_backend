package fr.slghive.heartlink.dtos.address;

public record AddressRequestDto(
                String street,
                String city,
                String department,
                String region,
                String streetNumber,
                String zipCode) {
}
