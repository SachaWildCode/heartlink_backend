package fr.slghive.heartlink.dtos.organizations.organization_post;

import fr.slghive.heartlink.dtos.address_organization.address_organization_post.AddressOrganizationPostResponse;
import fr.slghive.heartlink.dtos.type.type_post.TypePostResponse;
import fr.slghive.heartlink.entities.AddressOrganization;
import fr.slghive.heartlink.entities.OrganizationEntity;
import fr.slghive.heartlink.entities.TypeEntity;

public class OrganizationPostMapper {

  public static OrganizationEntity toEntity(OrganizationPostRequest dto) {

    OrganizationEntity entity = new OrganizationEntity();
    AddressOrganization addressOrganization = new AddressOrganization();
    TypeEntity type = new TypeEntity();

    Identite identite = dto.identite();
    Activites activites = dto.activites();
    Coordonnees coordonnees = dto.coordonnees();

    Adresse adresseGestion = coordonnees.adresseGestion();
    addressOrganization.setPays(adresseGestion.pays());
    addressOrganization.setCplt1(adresseGestion.cplt1());
    addressOrganization.setCp(adresseGestion.cp());
    addressOrganization.setCommune(adresseGestion.commune());
    addressOrganization.setCodeInsee(adresseGestion.codeInsee());

    type.setLibTheme(activites.libTheme1());

    entity.setActive(identite.active());
    entity.setGroupement(identite.groupement());
    entity.setSigle(identite.sigle());
    entity.setIdRna(identite.idRna());
    entity.setIdEx(identite.idEx());
    entity.setName(identite.nom());
    entity.setCreationDate(identite.dateCreat());
    entity.setDateModifRna(identite.dateModifRna());
    entity.setRegime(identite.regime());
    entity.setNature(identite.nature());
    entity.setUtilPublique(identite.utilPublique());
    entity.setEligibiliteCec(identite.eligibiliteCec());
    entity.setActiveSirene(identite.activeSirene());
    entity.setDescription(activites.objet());

    entity.setAddressGestion(addressOrganization);
    entity.setType(type);

    return entity;
  }

  public static OrganizationPostResponse toDto(OrganizationEntity entity) {
    return new OrganizationPostResponse(
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
        new AddressOrganizationPostResponse(
            entity.getAddressGestion().getId(),
            entity.getAddressGestion().getPays(),
            entity.getAddressGestion().getCplt1(),
            entity.getAddressGestion().getCp(),
            entity.getAddressGestion().getCommune(),
            entity.getAddressGestion().getCodeInsee()),
        new TypePostResponse(
            entity.getType().getId(),
            entity.getType().getLibTheme()));

  }

}