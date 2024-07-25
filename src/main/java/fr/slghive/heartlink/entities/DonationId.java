package fr.slghive.heartlink.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DonationId implements Serializable {

    @Column(name = "organization_id")
    private Integer organizationId;

    @Column(name = "user_id")
    private Integer userId;
}