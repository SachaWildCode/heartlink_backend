package fr.slghive.heartlink.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.slghive.heartlink.dtos.donations.donations_get.DonationGetMapper;
import fr.slghive.heartlink.dtos.donations.donations_get.DonationGetResponse;
import fr.slghive.heartlink.dtos.donations.donations_post.DonationPostMapper;
import fr.slghive.heartlink.dtos.donations.donations_post.DonationPostRequest;
import fr.slghive.heartlink.dtos.donations.donations_post.DonationPostResponse;
import fr.slghive.heartlink.dtos.donations.donations_post.DonationPostResponseWrapper;
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

    public List<DonationGetResponse> getDonations(UserDetails userDetails) {
        UserEntity user = userRepository.findByAccount_Email(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<DonationEntity> donations = donationRepository.findByUser(user);
        return donations.stream().map(DonationGetMapper::toDto).collect(Collectors.toList());
    }

    // public List<DonationPostResponse> setDonations(UserDetails userDetails,
    // List<DonationPostRequest> donationRequests) {

    // UserEntity user =
    // userRepository.findByAccount_Email(userDetails.getUsername())
    // .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    // List<DonationPostResponse> responses = new ArrayList<>();

    // for (DonationPostRequest dto : donationRequests) {

    // DonationId donationId = new DonationId(dto.organizationId(), user.getId());
    // DonationEntity donation = donationRepository.findById(donationId)
    // .orElseGet(DonationEntity::new);
    // donation.setId(donationId);
    // donation.setUser(user);
    // donation.setOrganization(organizationRepository.findById(dto.organizationId())
    // .orElseThrow(() -> new ResourceNotFoundException("Organization not found")));

    // donation.setAmount(dto.amount());
    // donation.setPercentageAttribution(dto.percentageAttribution());
    // if (donation.getPercentageAttribution() > 0) {
    // donationRepository.save(donation);

    // }

    // responses.add(DonationPostMapper.toDto(donation));
    // }

    // return responses;
    // }
    // }
    public DonationPostResponseWrapper setDonations(UserDetails userDetails,
            List<DonationPostRequest> donationRequests) {

        UserEntity user = userRepository.findByAccount_Email(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<DonationPostResponse> successfulDonations = new ArrayList<>();
        List<String> failedDonations = new ArrayList<>();

        for (DonationPostRequest dto : donationRequests) {
            DonationId donationId = new DonationId(dto.organizationId(), user.getId());
            DonationEntity donation = donationRepository.findById(donationId)
                    .orElseGet(DonationEntity::new);
            donation.setId(donationId);
            donation.setUser(user);
            donation.setOrganization(organizationRepository.findById(dto.organizationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Organization not found")));

            donation.setAmount(dto.amount());
            donation.setPercentageAttribution(dto.percentageAttribution());

            if (donation.getPercentageAttribution() > 0) {
                donationRepository.save(donation);
                successfulDonations.add(DonationPostMapper.toDto(donation));
            } else {
                failedDonations.add(donation.getOrganization().getName());
            }
        }

        return new DonationPostResponseWrapper(successfulDonations, failedDonations);
    }
}