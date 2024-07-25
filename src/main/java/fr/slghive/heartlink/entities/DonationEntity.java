package fr.slghive.heartlink.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "donations")
public class DonationEntity {

    @EmbeddedId
    private DonationId id;

    // TODO implement stripe and remove nullable true
    @Column(nullable = true)
    private Integer amount;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private Float percentageAttribution;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updateDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("organizationId")
    @JoinColumn(name = "organization_id", nullable = false)
    private OrganizationEntity organization;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public DonationEntity(OrganizationEntity organization, UserEntity user) {
        this.id = new DonationId(organization.getId(), user.getId());
        this.organization = organization;
        this.user = user;
        this.creationDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
        this.percentageAttribution = 0f;
    }
}
