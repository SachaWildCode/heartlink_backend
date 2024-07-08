package fr.slghive.heartlink.dtos.user_post.user_address;

public record UserAddressPostRequestDto(
        String street,
        String city,
        String department,
        String region,
        String streetNumber,
        String zipCode) {
}
