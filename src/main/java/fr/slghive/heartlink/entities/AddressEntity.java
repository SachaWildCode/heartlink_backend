package fr.slghive.heartlink.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(name = "addresses", uniqueConstraints = {
        @UniqueConstraint(name = "UniqueAddress", columnNames = { "street", "city", "zip_code", "street_number" })
})

public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String street;

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

}
