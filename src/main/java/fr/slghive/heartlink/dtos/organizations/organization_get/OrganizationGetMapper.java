package fr.slghive.heartlink.dtos.organizations.organization_get;

import fr.slghive.heartlink.dtos.address_organization.address_organization_get.AddressOrganizationGetMapper;
import fr.slghive.heartlink.dtos.type.type_get.TypeGetMapper;
import fr.slghive.heartlink.entities.OrganizationEntity;

public class OrganizationGetMapper {
    public static OrganizationGetResponse toDto(OrganizationEntity entity) {
        return new OrganizationGetResponse(
                entity.getId(),
                entity.getName(),
                entity.getSigle(),
                entity.getDescription(),
                entity.getIban(),
                entity.getCreationDate(),
                entity.getGroupement(),
                entity.getIdRna(),
                entity.getIdEx(),
                entity.getDateModifRna(),
                entity.getRegime(),
                entity.getNature(),
                entity.isUtilPublique(),
                entity.isEligibiliteCec(),
                entity.isActiveSirene(),
                entity.isActive(),
                entity.isImpotsCommerciaux(),
                AddressOrganizationGetMapper.toDto(entity.getAddressGestion()),
                TypeGetMapper.toDto(entity.getType()));
    }
}
