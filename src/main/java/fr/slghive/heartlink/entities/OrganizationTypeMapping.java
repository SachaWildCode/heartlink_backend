package fr.slghive.heartlink.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "organization_type_mapping")
public class OrganizationTypeMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
