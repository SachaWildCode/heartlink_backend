package fr.slghive.heartlink.dtos.user_post.user_address;

public record UserAddressPostResponseDto(
                String street,
                String city,
                String zipCode,
                String department,
                String region,
                String streetNumber) {
}
