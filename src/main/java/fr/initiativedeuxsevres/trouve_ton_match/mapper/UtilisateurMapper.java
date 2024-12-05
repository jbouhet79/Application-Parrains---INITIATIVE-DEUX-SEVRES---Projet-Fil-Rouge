package fr.initiativedeuxsevres.trouve_ton_match.mapper;

import fr.initiativedeuxsevres.trouve_ton_match.dto.SecteurReseauDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.TypeAccompagnementDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.UtilisateurDto;
import fr.initiativedeuxsevres.trouve_ton_match.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UtilisateurMapper {

    /**
     * Convertit une entité Utilisateur en UtilisateurDto.
     *
     * @param utilisateur L'entité Utilisateur à convertir.
     * @return Un UtilisateurDto correspondant.
     */
    public static UtilisateurDto toDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setIdUtilisateur(utilisateur.getIdUtilisateur());
        utilisateurDto.setNomUtilisateur(utilisateur.getNomUtilisateur());
        utilisateurDto.setPrenomUtilisateur(utilisateur.getPrenomUtilisateur());
        utilisateurDto.setEntrepriseUtilisateur(utilisateur.getEntrepriseUtilisateur());
        utilisateurDto.setPlateformeUtilisateur(utilisateur.getPlateformeUtilisateur());
        utilisateurDto.setCodeUtilisateur(utilisateur.getCodeUtilisateur());
        utilisateurDto.setTypeUtilisateur(utilisateur.getTypeUtilisateur());

        // Convertir accompagnementTypeList en une liste d'IDs
        if (utilisateur.getAccompagnementTypeList() != null) {
            utilisateurDto.setAccompagnementTypeList(
                    utilisateur.getAccompagnementTypeList().stream()
                            .map(TypeAccompagnement::getId) // Récupérer l'ID de chaque TypeAccompagnement
                            .collect(Collectors.toList())
            );
        }

        // Convertir secteurReseauList en une liste d'IDs
        if (utilisateur.getSecteurReseauList() != null) {
            utilisateurDto.setSecteurReseauList(
                    utilisateur.getSecteurReseauList().stream()
                            .map(SecteurReseau::getId) // Récupérer l'ID de chaque SecteurReseau
                            .collect(Collectors.toList())
            );
        }

        return utilisateurDto;
    }

    /**
     * Convertit un UtilisateurDto en entité Utilisateur.
     *
     * @param utilisateurDto Le DTO à convertir.
     * @param accompagnements La liste des entités TypeAccompagnement correspondantes.
     * @param secteursReseaux La liste des entités SecteurReseau correspondantes.
     * @return Une entité Utilisateur correspondant.
     */
    public static Utilisateur toEntity(UtilisateurDto utilisateurDto, List<TypeAccompagnementDto> accompagnements,
                                       List<SecteurReseauDto> secteursReseaux) {
        if (utilisateurDto == null) {
            return null;
        }

        // Utilisation d'un constructeur
//        Utilisateur utilisateur = new Utilisateur() {
//            // Classe anonyme car Utilisateur est abstrait
//        };

        Utilisateur utilisateur;
        if (utilisateurDto.getTypeUtilisateur().equals("parrain")) {
            utilisateur = new Parrain(); // Créer une instance de Parrain
        } else {
            utilisateur = new Porteur(); // Créer une instance de Porteur
        }

        // Mapping des champs
        utilisateur.setIdUtilisateur(utilisateurDto.getIdUtilisateur());
        utilisateur.setNomUtilisateur(utilisateurDto.getNomUtilisateur());
        utilisateur.setPrenomUtilisateur(utilisateurDto.getPrenomUtilisateur());
        utilisateur.setEntrepriseUtilisateur(utilisateurDto.getEntrepriseUtilisateur());
        utilisateur.setPlateformeUtilisateur(utilisateurDto.getPlateformeUtilisateur());
        utilisateur.setCodeUtilisateur(utilisateurDto.getCodeUtilisateur());
        utilisateur.setTypeUtilisateur(utilisateurDto.getTypeUtilisateur());
        System.out.println("utilisateurMapper: " + utilisateur);

        // Associer les entités accompagnementTypeList
//        if (accompagnements != null) {
//            utilisateur.setAccompagnementTypeList(accompagnements);
//        }

        // Associer les entités secteurReseauList
//        if (secteursReseaux != null) {
//            utilisateur.setSecteurReseauList(secteursReseaux); // setSecteurReseauList(List<SecteurReseauDto> secteurReseauList)
//        }

        return utilisateur;
    }
}
