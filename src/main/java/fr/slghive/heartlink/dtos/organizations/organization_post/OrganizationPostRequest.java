package fr.slghive.heartlink.dtos.organizations.organization_post;

import fr.slghive.heartlink.dtos.address.address_post.AddressPostRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record OrganizationPostRequest(
    @NotBlank String socialName,
    @NotBlank String description,
    @NotBlank String iban,
    @Valid AddressPostRequest address) {
}
