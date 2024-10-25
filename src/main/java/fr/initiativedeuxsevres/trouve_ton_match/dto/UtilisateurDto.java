package fr.initiativedeuxsevres.trouve_ton_match.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDto {

    /**
     * Propriétés protected pour que les classes héritants de UtilisateurDto puissent les utiliser
     */
    protected Long idUtilisateur;
    protected String nomUtilisateur;
    protected String prenomUtilisateur;
    protected String entrepriseUtilisateur;
    protected String plateformeUtilisateur;
    protected String codeUtilisateur;
    protected String type;


}
