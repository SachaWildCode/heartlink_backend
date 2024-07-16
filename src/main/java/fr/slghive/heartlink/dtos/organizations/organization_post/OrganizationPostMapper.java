package fr.slghive.heartlink.dtos.organizations.organization_post;

import java.util.stream.Collectors;

import fr.slghive.heartlink.dtos.address.address_post.AddressPostMapper;
import fr.slghive.heartlink.dtos.type.type_post.TypePostMapper;
import fr.slghive.heartlink.entities.OrganizationEntity;

public class OrganizationPostMapper {

  public static OrganizationPostResponse toDto(OrganizationEntity entity) {
    return new OrganizationPostResponse(
        entity.getId(),
        entity.getSocialName(),
        entity.getDescription(),
        entity.getIban(),
        entity.getTypes().stream()
            .map(TypePostMapper::toDto)
            .collect(Collectors.toSet()),
        AddressPostMapper.toDto(entity.getAddress()));
  }

  public static OrganizationEntity toEntity(OrganizationPostRequest dto) {
    OrganizationEntity organization = new OrganizationEntity();
    organization.setSocialName(dto.socialName());
    organization.setDescription(dto.description());
    organization.setIban(dto.iban());
    organization.setAddress(AddressPostMapper.toEntity(dto.address()));
    organization.setTypes(dto.types().stream()
        .map(TypePostMapper::toEntity)
        .collect(Collectors.toSet()));
    return organization;
  }
}