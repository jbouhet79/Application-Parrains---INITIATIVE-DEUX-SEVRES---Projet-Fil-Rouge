package fr.initiativedeuxsevres.trouve_ton_match.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
//  Indique que les méthodes equals et hashCode ne doivent pas inclure les champs de la superclasse (qui peut poser problème en cas d'héritage de classe).
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PorteurDto extends UtilisateurDto {

    /**
     * Propriétés
     */
    private String dateLancement;
    private String domaine;
    private String besoins;
    private String lieuActivite;
    private String disponibilites;

    /**
     * Constructeur d'un porteur (compte utilisateur + paramètres propres au porteur)
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
    public PorteurDto(Long idUtilisateur, String nomUtilisateur, String prenomUtilisateur,
                            String entrepriseUtilisateur,
                            String plateformeUtilisateur, String codeUtilisateur, String dateLancement,
                            String domaine, String besoins, String lieuActivite, String disponibilites) {

        // propriétés de la classe mère
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.entrepriseUtilisateur = entrepriseUtilisateur;
        this.plateformeUtilisateur = plateformeUtilisateur;
        this.codeUtilisateur = codeUtilisateur;

        // propriétés de la classe fille
        this.dateLancement = dateLancement;
        this.domaine = domaine;
        this.besoins = besoins;
        this.lieuActivite = lieuActivite;
        this.disponibilites = disponibilites;

    }

    /**
     * Contructeur d'un utilisateur de type : Porteur
     * @param utilisateurDto
     */
    public PorteurDto(UtilisateurDto utilisateurDto)
    {
        this.idUtilisateur = utilisateurDto.getIdUtilisateur();
        this.nomUtilisateur = utilisateurDto.getNomUtilisateur();
        this.prenomUtilisateur = utilisateurDto.getPrenomUtilisateur();
        this.entrepriseUtilisateur = utilisateurDto.getEntrepriseUtilisateur();
        this.plateformeUtilisateur = utilisateurDto.getPlateformeUtilisateur();
        this.codeUtilisateur = utilisateurDto.getCodeUtilisateur();

    }
}


