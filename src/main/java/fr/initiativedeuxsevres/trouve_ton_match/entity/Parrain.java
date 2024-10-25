package fr.initiativedeuxsevres.trouve_ton_match.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    public Parrain (Long idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String entrepriseUtilisateur,
                    String plateformeUtilisateur, String codeUtilisateur, String presentationParcours,
                    String branchesReseau, String domainesExpertise, String secteurGeographique, String disponibilites
                    )
    {
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.entrepriseUtilisateur = entrepriseUtilisateur;
        this.plateformeUtilisateur = plateformeUtilisateur;
        this.codeUtilisateur = codeUtilisateur;

        this.presentationParcours = presentationParcours;
        this.branchesReseau = branchesReseau;
        this.domainesExpertise = domainesExpertise;
        this.secteurGeographique = secteurGeographique;
        this.disponibilites = disponibilites;
    }
        



}
