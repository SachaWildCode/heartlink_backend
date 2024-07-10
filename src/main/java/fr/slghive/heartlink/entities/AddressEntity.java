package fr.slghive.heartlink.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "addresses")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String streetType;

    @Column(nullable = false, length = 100)
    private String streetName;

    @Column(nullable = false, length = 5)
    private String streetNumber;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String region;

    @Column(nullable = false, length = 100)
    private String department;

    @Column(nullable = false, length = 6)
    private String zipCode;

    @OneToMany(mappedBy = "address")
    private List<UserEntity> users;

    @OneToMany(mappedBy = "address")
    private List<OrganizationEntity> organizations;
}
