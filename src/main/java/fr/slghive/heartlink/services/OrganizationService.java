package fr.slghive.heartlink.services;

import fr.slghive.heartlink.dtos.organizations.organization_get.OrganizationGetMapper;
import fr.slghive.heartlink.dtos.organizations.organization_get.OrganizationGetResponse;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostMapper;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostRequest;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostResponse;
import fr.slghive.heartlink.entities.OrganizationEntity;
import fr.slghive.heartlink.exceptions.DuplicateException;
import fr.slghive.heartlink.exceptions.ResourceNotFoundException;
import fr.slghive.heartlink.repositories.OrganizationRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

  private final OrganizationRepository organizationRepository;

  public OrganizationService(OrganizationRepository organizationRepository) {
    this.organizationRepository = organizationRepository;
  }

  public List<OrganizationGetResponse> getAllOrganizations() {
    if (organizationRepository.findAll().isEmpty()) {
      throw new ResourceNotFoundException("No organizations found");
    } else {
      return organizationRepository
        .findAll()
        .stream()
        .map(OrganizationGetMapper::toDto)
        .toList();
    }
  }

  public OrganizationPostResponse createOrganization(
    OrganizationPostRequest organizationPostRequest
  ) {
    OrganizationEntity organization = OrganizationPostMapper.toEntity(
      organizationPostRequest
    );
    organizationRepository
      .findBySocialName(organization.getSocialName())
      .ifPresent(e -> {
        throw new DuplicateException("Organization already exists");
      });
    organization = organizationRepository.save(organization);
    return OrganizationPostMapper.toDto(organization);
  }
}
