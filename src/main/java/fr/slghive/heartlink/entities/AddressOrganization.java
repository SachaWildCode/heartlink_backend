package fr.slghive.heartlink.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address_organization")
@Getter
@Setter

public class AddressOrganization {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    private String cplt1;

    @Column(nullable = true)
    private String cp;

    @Column(nullable = true)
    private String commune;

    @Column(nullable = true)
    private String codeInsee;

    @Column(nullable = true)
    private String pays;

    @OneToOne(mappedBy = "addressGestion")
    private OrganizationEntity organization;
}