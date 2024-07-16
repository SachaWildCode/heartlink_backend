package fr.slghive.heartlink.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import fr.slghive.heartlink.dtos.organizations.organization_get.OrganizationGetMapper;
import fr.slghive.heartlink.dtos.organizations.organization_get.OrganizationGetResponse;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostMapper;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostRequest;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostResponse;
import fr.slghive.heartlink.entities.OrganizationEntity;
import fr.slghive.heartlink.entities.TypeEntity;
import fr.slghive.heartlink.exceptions.ResourceNotFoundException;
import fr.slghive.heartlink.repositories.OrganizationRepository;
import fr.slghive.heartlink.repositories.TypeRepository;

@Service
public class OrganizationService {

  private final OrganizationRepository organizationRepository;
  private final TypeRepository typeRepository;

  public OrganizationService(OrganizationRepository organizationRepository, TypeRepository typeRepository) {
    this.organizationRepository = organizationRepository;
    this.typeRepository = typeRepository;
  }

  public List<OrganizationGetResponse> getAllOrganizations() {
    if (organizationRepository.findAll().isEmpty()) {
      throw new ResourceNotFoundException("No organizations found");
    }
    return organizationRepository.findAll()
        .stream()
        .map(OrganizationGetMapper::toDto)
        .toList();
  }

  public OrganizationPostResponse saveOrganization(OrganizationPostRequest organizationPostRequest) {
    Set<TypeEntity> types = new HashSet<>();
    organizationPostRequest.types().forEach(typeRequest -> {
      Optional<TypeEntity> type = typeRepository.findByName(typeRequest.name());
      if (type.isEmpty()) {
        TypeEntity newType = new TypeEntity();
        newType.setName(typeRequest.name());
        typeRepository.save(newType);
        types.add(newType);
      } else {
        types.add(type.get());
      }
    });
    OrganizationEntity organization = OrganizationPostMapper.toEntity(organizationPostRequest);
    organization.setTypes(types.stream().toList());
    organizationRepository.save(organization);
    return OrganizationPostMapper.toDto(organization);
  }
}