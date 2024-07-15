package fr.slghive.heartlink.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.slghive.heartlink.dtos.organizations.organization_get.OrganizationGetMapper;
import fr.slghive.heartlink.dtos.organizations.organization_get.OrganizationGetResponse;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostMapper;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostRequest;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostResponse;
import fr.slghive.heartlink.entities.OrganizationEntity;
import fr.slghive.heartlink.exceptions.DuplicateException;
import fr.slghive.heartlink.exceptions.ResourceNotFoundException;
import fr.slghive.heartlink.repositories.OrganizationRepository;

@Service
public class OrganizationService {

  private final OrganizationRepository organizationRepository;

  public OrganizationService(OrganizationRepository organizationRepository) {
    this.organizationRepository = organizationRepository;
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

  public OrganizationPostResponse createOrganization(OrganizationPostRequest organizationPostRequest) {
    if (organizationRepository.existsBySocialName(organizationPostRequest.socialName())) {
      throw new DuplicateException("Organization already exists");
    }
    OrganizationEntity organization = OrganizationPostMapper.toEntity(organizationPostRequest);
    organization = organizationRepository.save(organization);
    return OrganizationPostMapper.toDto(organization);
  }
}