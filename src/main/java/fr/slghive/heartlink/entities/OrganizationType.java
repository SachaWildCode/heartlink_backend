package fr.slghive.heartlink.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "organization_type_mapping")
public class OrganizationType {

    @EmbeddedId
    private OrganizationTypeId id;

    @ManyToOne
    @MapsId("organizationId")
    @JoinColumn(name = "organization_id", referencedColumnName = "id", insertable = false, updatable = false)
    private OrganizationEntity organization;

    @ManyToOne
    @MapsId("typeId")
    @JoinColumn(name = "type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TypeEntity type;

    public OrganizationType(OrganizationEntity organizationEntity, TypeEntity typeEntity) {
        this.id = new OrganizationTypeId(organizationEntity.getId(), typeEntity.getId());
        this.organization = organizationEntity;
        this.type = typeEntity;
    }
}
