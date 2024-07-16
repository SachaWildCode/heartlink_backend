package fr.slghive.heartlink.dtos.organizations.organization_post;

import java.util.List;

import fr.slghive.heartlink.dtos.address.address_post.AddressPostRequest;
import fr.slghive.heartlink.dtos.type.type_post.TypePostRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record OrganizationPostRequest(
                @NotBlank String socialName,
                @NotBlank String description,
                @NotBlank String iban,
                @Valid List<TypePostRequest> types,
                @Valid AddressPostRequest address) {
}
