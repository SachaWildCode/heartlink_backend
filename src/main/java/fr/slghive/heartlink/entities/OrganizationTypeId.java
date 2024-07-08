package fr.slghive.heartlink.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrganizationTypeId implements Serializable {

    @Column(name = "organization_id")
    private Integer organizationId;

    @Column(name = "type_id")
    private Integer typeId;

}