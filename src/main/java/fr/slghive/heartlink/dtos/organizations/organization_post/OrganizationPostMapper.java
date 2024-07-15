package fr.slghive.heartlink.dtos.organizations.organization_post;

import fr.slghive.heartlink.dtos.address.address_post.AddressPostMapper;
import fr.slghive.heartlink.entities.OrganizationEntity;

public class OrganizationPostMapper {

  public static OrganizationPostResponse toDto(OrganizationEntity entity) {
    return new OrganizationPostResponse(
        entity.getId(),
        entity.getSocialName(),
        entity.getDescription(),
        entity.getIban(),
        AddressPostMapper.toDto(entity.getAddress()));
  }

  public static OrganizationEntity toEntity(OrganizationPostRequest dto) {
    OrganizationEntity organization = new OrganizationEntity();
    organization.setSocialName(dto.socialName());
    organization.setDescription(dto.description());
    organization.setIban(dto.iban());
    organization.setAddress(AddressPostMapper.toEntity(dto.address()));
    return organization;
  }
}
