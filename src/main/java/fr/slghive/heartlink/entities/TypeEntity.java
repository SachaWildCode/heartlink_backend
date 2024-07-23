package fr.slghive.heartlink.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "types", uniqueConstraints = {
        @UniqueConstraint(columnNames = "libTheme")
})
@Getter
@Setter
public class TypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    private String color;

    @NotBlank
    @Column(nullable = true, unique = true)
    private String libTheme;

    @OneToMany(mappedBy = "type")
    private List<OrganizationEntity> organizations;
}
