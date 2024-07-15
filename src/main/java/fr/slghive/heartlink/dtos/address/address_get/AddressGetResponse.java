package fr.slghive.heartlink.dtos.address.address_get;

public record AddressGetResponse(
                String streetNumber,
                String streetType,
                String streetName,
                String city,
                String region,
                String department,
                String zipCode) {

}
