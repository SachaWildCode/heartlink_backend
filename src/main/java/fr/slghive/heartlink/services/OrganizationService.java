package fr.slghive.heartlink.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import fr.slghive.heartlink.dtos.organizations.organization_get.OrganizationGetMapper;
import fr.slghive.heartlink.dtos.organizations.organization_get.OrganizationGetResponse;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostMapper;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostRequest;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostResponse;
import fr.slghive.heartlink.entities.OrganizationEntity;
import fr.slghive.heartlink.entities.TypeEntity;
import fr.slghive.heartlink.exceptions.DuplicateException;
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
    List<OrganizationEntity> organizations = organizationRepository.findAll();
    if (organizations.isEmpty()) {
      throw new ResourceNotFoundException("No organizations found");
    }
    return organizations.stream()
        .map(OrganizationGetMapper::toDto)
        .toList();
  }

  public OrganizationPostResponse saveOrganization(OrganizationPostRequest organizationPostRequest) {
    Set<TypeEntity> types = new HashSet<>();
    organizationPostRequest.types().forEach(typeRequest -> {
      TypeEntity type = typeRepository.findByName(typeRequest.name())
          .orElseGet(() -> {
            TypeEntity newType = new TypeEntity();
            newType.setName(typeRequest.name());
            return typeRepository.save(newType);
          });
      types.add(type);
    });
    OrganizationEntity organization = OrganizationPostMapper.toEntity(organizationPostRequest);
    organization.setTypes(types.stream().toList());
    try {
      organizationRepository.save(organization);
    } catch (DataIntegrityViolationException e) {
      throw new DuplicateException("Organization already exists");
    }
    return OrganizationPostMapper.toDto(organization);
  }
}