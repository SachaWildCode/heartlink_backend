package fr.slghive.heartlink.dtos.organizations.organization_post;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrganizationPostRequest(
        @JsonProperty("coordonnees") Coordonnees coordonnees,
        @JsonProperty("activites") Activites activites,
        @JsonProperty("identite") Identite identite) {
}

record Activites(
        String objet,
        @JsonProperty("lib_famille1") String libTheme1) {
}

record Coordonnees(
        @JsonProperty("adresse_gestion") Adresse adresseGestion) {
}

record Adresse(
        String pays,
        @JsonProperty("cplt_1") String cplt1,
        String cp,
        String commune,
        @JsonProperty("code_insee") String codeInsee) {
}

record Identite(
        String groupement,
        String sigle,
        @JsonProperty("id_rna") String idRna,
        @JsonProperty("id_ex") String idEx,
        String nom,
        @JsonProperty("date_creat") LocalDate dateCreat,
        @JsonProperty("date_modif_rna") LocalDate dateModifRna,
        String regime,
        String nature,
        @JsonProperty("util_publique") boolean utilPublique,
        @JsonProperty("eligibilite_cec") boolean eligibiliteCec,
        @JsonProperty("active_sirene") boolean activeSirene,
        boolean active) {
}
