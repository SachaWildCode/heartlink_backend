package fr.slghive.heartlink.entities;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "organizations")
@Getter
@Setter
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DonationEntity> donations;

    @Column(nullable = false)
    private String sigle;

    @Column(nullable = true, columnDefinition = "LONGTEXT")
    private String description;

    @Column(nullable = true)
    private String iban;

    @Column(nullable = true)
    private LocalDate creationDate;

    @Column(nullable = true)
    private String groupement;

    @Column(nullable = true)
    private String idRna;

    @Column(nullable = true)
    private String idEx;

    @Column(nullable = true)
    private LocalDate dateModifRna;

    @Column(nullable = true)
    private String regime;

    @Column(nullable = true)
    private String nature;

    @Column(nullable = false)
    private boolean utilPublique;

    @Column(nullable = false)
    private boolean eligibiliteCec;

    @Column(nullable = false)
    private boolean activeSirene;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean impotsCommerciaux;

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_organization_id", referencedColumnName = "id")
    private AddressOrganization addressGestion;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TypeEntity type;
}