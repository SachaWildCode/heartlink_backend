package fr.slghive.heartlink.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.slghive.heartlink.dtos.organizations.organization_get.OrganizationGetResponse;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostRequest;
import fr.slghive.heartlink.dtos.organizations.organization_post.OrganizationPostResponse;
import fr.slghive.heartlink.services.OrganizationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("")
    public ResponseEntity<List<OrganizationGetResponse>> getAllOrganizations() {
        return ResponseEntity.ok(organizationService.getAllOrganizations());
    }

    @PostMapping("")
    public ResponseEntity<OrganizationPostResponse> createOrganization(
            @Valid @RequestBody OrganizationPostRequest organizationPostRequest) {
        return ResponseEntity.ok(organizationService.createOrganization(organizationPostRequest));
    }

}
