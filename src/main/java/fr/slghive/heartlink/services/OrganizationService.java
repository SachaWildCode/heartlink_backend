package fr.slghive.heartlink.services;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.slghive.heartlink.dtos.organizations.organization_get.OrganizationGetMapper;
import fr.slghive.heartlink.dtos.organizations.organization_get.OrganizationGetResponse;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostMapper;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostRequest;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostResponse;
import fr.slghive.heartlink.entities.OrganizationEntity;
import fr.slghive.heartlink.entities.TypeEntity;
import fr.slghive.heartlink.repositories.OrganizationRepository;
import fr.slghive.heartlink.repositories.TypeRepository;
import jakarta.transaction.Transactional;

@Service
public class OrganizationService {

  private final OrganizationRepository organizationRepository;
  private final TypeRepository typeRepository;
  // C'est pas bien de faire ça
  private final Random random = new Random(); // Reuse this instance

  public OrganizationService(OrganizationRepository organizationRepository, TypeRepository typeRepository) {
    this.organizationRepository = organizationRepository;
    this.typeRepository = typeRepository;
  }

  public Page<OrganizationGetResponse> getAllOrganizations(Integer page) {
    Integer size = 50;
    Pageable pageable = PageRequest.of(page, size);
    Page<OrganizationEntity> pageOrganization = organizationRepository.findAll(pageable);
    return pageOrganization.map(OrganizationGetMapper::toDto);
  }

  @Transactional
  public OrganizationPostResponse createOrganization(OrganizationPostRequest organizationPostRequest) {
    OrganizationEntity organization = OrganizationPostMapper.toEntity(organizationPostRequest);

    // Fetch type or create if not exists
    TypeEntity type;
    try {
      type = typeRepository.findByLibTheme(organization.getType().getLibTheme())
          .orElseGet(() -> {
            TypeEntity newType = new TypeEntity();
            newType.setLibTheme(organization.getType().getLibTheme());
            return typeRepository.save(newType);
          });
    } catch (Exception e) {
      return null;
    }
    organization.setType(type);

    // Check if organization already exists
    if (organizationRepository.existsByName(organization.getName())) {
      return null;
    }

    // Save organization and handle exceptions
    try {
      // generate random iban and add it to organization
      organization.setIban(generateIban());
      organizationRepository.save(organization);
    } catch (Exception e) {
      return null; // Return null if save fails
    }

    return OrganizationPostMapper.toDto(organization);
  }

  public String generateIban() {
    String countryCode = "FR";
    String checkDigits = String.format("%02d", random.nextInt(100)); // Generate two check digits
    String bankCode = String.format("%04d", random.nextInt(10000)); // Generate a 4-digit bank code
    String branchCode = String.format("%05d", random.nextInt(100000)); // Generate a 5-digit branch code
    String accountNumber = String.format("%011d", random.nextLong(100000000000L)); // Generate an 11-digit account
                                                                                   // number
    String nationalCheckDigits = String.format("%02d", random.nextInt(100)); // Generate two national check digits

    return countryCode + checkDigits + bankCode + branchCode + accountNumber + nationalCheckDigits;
  }

  public List<OrganizationPostResponse> createOrganizations(List<OrganizationPostRequest> organizationPostRequests) {
    return organizationPostRequests.stream()
        .map(this::createOrganization)
        .filter(Objects::nonNull) // Filter out null responses
        .collect(Collectors.toList());
  }

}