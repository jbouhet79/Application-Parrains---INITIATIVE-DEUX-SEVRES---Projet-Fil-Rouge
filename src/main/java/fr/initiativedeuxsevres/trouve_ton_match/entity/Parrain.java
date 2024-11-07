package fr.initiativedeuxsevres.trouve_ton_match.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
//@AllArgsConstructor
//@NoArgsConstructor
@SuperBuilder
@Table(name= "parrain")
public class Parrain extends Utilisateur {

    /**
     * Propriétés de la classe
     */
    private String presentationParcours;
    private String branchesReseau;
    private String domainesExpertise;
    private String secteurGeographique;
    private String disponibilites;

    /**
     * Constructeur avec tous les paramètres de la classe mère
     * @param idUtilisateur
     * @param nomUtilisateur
     * @param prenomUtilisateur
     * @param entrepriseUtilisateur
     * @param plateformeUtilisateur
     * @param codeUtilisateur
     * @param presentationParcours
     * @param branchesReseau
     * @param domainesExpertise
     * @param secteurGeographique
     * @param disponibilites
     */

    public Parrain(Long idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String entrepriseUtilisateur,
                   String plateformeUtilisateur, String codeUtilisateur, String presentationParcours,
                   String branchesReseau, String domainesExpertise, String secteurGeographique, String disponibilites) {

        // Appel au constructeur de la classe mère
        super(idUtilisateur, nomUtilisateur, prenomUtilisateur, entrepriseUtilisateur, plateformeUtilisateur, codeUtilisateur);

        this.presentationParcours = presentationParcours;
        this.branchesReseau = branchesReseau;
        this.domainesExpertise = domainesExpertise;
        this.secteurGeographique = secteurGeographique;
        this.disponibilites = disponibilites;
    }

}
