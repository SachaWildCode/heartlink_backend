package fr.slghive.heartlink.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.slghive.heartlink.dtos.donations.donations_post.DonationPostMapper;
import fr.slghive.heartlink.dtos.donations.donations_post.DonationPostRequest;
import fr.slghive.heartlink.dtos.donations.donations_post.DonationPostResponse;
import fr.slghive.heartlink.entities.DonationEntity;
import fr.slghive.heartlink.entities.DonationId;
import fr.slghive.heartlink.entities.UserEntity;
import fr.slghive.heartlink.exceptions.ResourceNotFoundException;
import fr.slghive.heartlink.repositories.DonationRepository;
import fr.slghive.heartlink.repositories.OrganizationRepository;
import fr.slghive.heartlink.repositories.UserRepository;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    public DonationService(UserRepository userRepository, DonationRepository donationRepository,
            OrganizationRepository organizationRepository) {
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
        this.organizationRepository = organizationRepository;
    }

    public DonationPostResponse setDonation(UserDetails userDetails,
            DonationPostRequest dto) {
        UserEntity user = userRepository.findByAccount_Email(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        DonationEntity donation = new DonationEntity();

        donation.setId(new DonationId(user.getId(), dto.organizationId()));
        donation.setUser(user);
        donation.setOrganization(organizationRepository.findById(dto.organizationId())
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found")));
        donation.setPercentageAttribution(dto.percentageAttribution());
        donationRepository.save(donation);
        return DonationPostMapper.toDto(donation);
    }
}
