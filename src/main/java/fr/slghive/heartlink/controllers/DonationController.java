package fr.slghive.heartlink.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.slghive.heartlink.dtos.donations.donations_post.DonationPostRequest;
import fr.slghive.heartlink.dtos.donations.donations_post.DonationPostResponse;
import fr.slghive.heartlink.services.DonationService;

@RestController
@RequestMapping("/donations")
public class DonationController {
    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("")
    public ResponseEntity<DonationPostResponse> createDonation(@AuthenticationPrincipal UserDetails userDetails,
            @RequestBody DonationPostRequest donationPostRequest) {
        return ResponseEntity.ok(donationService.setDonation(userDetails, donationPostRequest));
    }

}
