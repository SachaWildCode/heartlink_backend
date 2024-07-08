package fr.slghive.heartlink.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class DonationId implements Serializable {

    @Column(name = "organization_id")
    private Integer organizationId;

    @Column(name = "user_id")
    private Integer userId;
}