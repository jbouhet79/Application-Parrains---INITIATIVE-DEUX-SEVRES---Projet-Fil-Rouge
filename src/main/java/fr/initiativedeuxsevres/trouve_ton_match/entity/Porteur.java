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
@Table(name= "porteur")
public class Porteur extends Utilisateur  {

    private String dateLancement;
    private String domaine;
    private String besoins;
    private String lieuActivite;
    private String disponibilites;

    /**
     * Constructeur avec tous les paramètres de la classe mère
     * @param idUtilisateur
     * @param nomUtilisateur
     * @param prenomUtilisateur
     * @param entrepriseUtilisateur
     * @param plateformeUtilisateur
     * @param codeUtilisateur
     * @param dateLancement
     * @param domaine
     * @param besoins
     * @param lieuActivite
     * @param disponibilites
     */
    public Porteur(Long idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String entrepriseUtilisateur,
                   String plateformeUtilisateur, String codeUtilisateur, String dateLancement, String domaine,
                   String besoins, String lieuActivite, String disponibilites) {

        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.entrepriseUtilisateur = entrepriseUtilisateur;
        this.plateformeUtilisateur = plateformeUtilisateur;
        this.codeUtilisateur = codeUtilisateur;

        this.dateLancement = dateLancement;
        this.domaine = domaine;
        this.besoins = besoins;
        this.lieuActivite = lieuActivite;
        this.disponibilites = disponibilites;
    }

}
