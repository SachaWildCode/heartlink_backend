package fr.slghive.heartlink.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.slghive.heartlink.dtos.donations.donations_get.DonationGetResponse;
import fr.slghive.heartlink.dtos.donations.donations_post.DonationPostRequest;
import fr.slghive.heartlink.dtos.donations.donations_post.DonationPostResponseWrapper;
import fr.slghive.heartlink.services.DonationService;

@RestController
@RequestMapping("/donations")
public class DonationController {
    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PutMapping("")
    public ResponseEntity<DonationPostResponseWrapper> createDonations(@AuthenticationPrincipal UserDetails userDetails,
            @RequestBody List<DonationPostRequest> donationPostRequests) {
        return ResponseEntity.ok(donationService.setDonations(userDetails, donationPostRequests));
    }

    @GetMapping("")
    public ResponseEntity<List<DonationGetResponse>> getDonations(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(donationService.getDonations(userDetails));
    }
}
