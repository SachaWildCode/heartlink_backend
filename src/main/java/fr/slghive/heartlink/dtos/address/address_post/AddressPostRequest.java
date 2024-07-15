package fr.slghive.heartlink.dtos.address.address_post;

import jakarta.validation.constraints.NotBlank;

public record AddressPostRequest(
                @NotBlank String streetNumber,
                @NotBlank String streetType,
                @NotBlank String streetName,
                @NotBlank String city,
                @NotBlank String region,
                @NotBlank String department,
                @NotBlank String zipCode) {
}
