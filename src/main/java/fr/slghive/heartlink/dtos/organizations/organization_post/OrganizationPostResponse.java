package fr.slghive.heartlink.dtos.organizations.organization_post;

import java.time.LocalDate;

import fr.slghive.heartlink.dtos.address_organization.address_organization_post.AddressOrganizationPostResponse;
import fr.slghive.heartlink.dtos.type.type_post.TypePostResponse;

public record OrganizationPostResponse(
                Integer id,
                String name,
                String sigle,
                String description,
                String iban,
                LocalDate creationDate,
                String groupement,
                String idRna,
                String idEx,
                LocalDate dateModifRna,
                String regime,
                String nature,
                boolean utilPublique,
                boolean eligibiliteCec,
                boolean activeSirene,
                boolean active,
                boolean impotsCommerciaux,
                AddressOrganizationPostResponse addressOrganization,
                TypePostResponse type) {

}
