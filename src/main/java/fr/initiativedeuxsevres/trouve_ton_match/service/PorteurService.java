package fr.initiativedeuxsevres.trouve_ton_match.service;

import fr.initiativedeuxsevres.trouve_ton_match.dto.ParrainDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.PorteurDto;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Parrain;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Porteur;
import fr.initiativedeuxsevres.trouve_ton_match.repository.PorteurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PorteurService {

    @Autowired
    private PorteurRepository porteurRepository;

    // 1ère version identique au ParrainService

//    public PorteurDto createPorteur (PorteurDto newPorteurDto) {
//
//        // On transforme un Dto en entité
//        Porteur nouveauPorteurEntity = new Porteur(
//                null,
//                newPorteurDto.getNomUtilisateur(),
//                newPorteurDto.getPrenomUtilisateur(),
//                newPorteurDto.getEntrepriseUtilisateur(),
//                newPorteurDto.getPlateformeUtilisateur(),
//                newPorteurDto.getCodeUtilisateur(),
//                newPorteurDto.getDateLancement(),
//                newPorteurDto.getDomaine(),
//                newPorteurDto.getBesoins(),
//                newPorteurDto.getLieuActivite(),
//                newPorteurDto.getDisponibilites()
//        );
//
//        Porteur saved = porteurRepository.save(nouveauPorteurEntity);
//
//        // On transforme l'entité sauvée en un nouveau Dto pour le renvoyer au front (via le controlleur)
//        return new PorteurDto(
//                saved.getIdUtilisateur(),
//                saved.getNomUtilisateur(),
//                saved.getPrenomUtilisateur(),
//                saved.getEntrepriseUtilisateur(),
//                saved.getPlateformeUtilisateur(),
//                saved.getCodeUtilisateur(),
//                saved.getDateLancement(),
//                saved.getDomaine(),
//                saved.getBesoins(),
//                saved.getLieuActivite(),
//                saved.getDisponibilites()
//        );
//    }

    // ou seconde version :

    public PorteurDto createPorteurOld(PorteurDto newPorteurDto) {

        // On transforme un Dto en entité (via la méthode privée porteurDtoToEntity décrite dessous)
        Porteur nouveauPorteurEntity = porteurDtoToEntity(newPorteurDto);

        Porteur nPorteur = porteurRepository.save(nouveauPorteurEntity);

        // On affecte l'id de l'entité nouvellement créée au Dto passé en paramètre
        // autre méthode que celle utilisée dans ParrainService
        // valable uniquement si seul l'id change (qui passe de nul à quelque chose)
        newPorteurDto.setIdUtilisateur(nPorteur.getIdUtilisateur());

        // On renvoie l'ancien Dto avec le nouvel id pour le renvoyer au front (via le controlleur)
        return newPorteurDto;
    }

    private static Porteur porteurDtoToEntity(PorteurDto newPorteurDto) {
        Porteur nouveauPorteurEntity = new Porteur(
                // --> id à null pour permettre la création d'autres comptes utilisateurs avec un nouvel id
                // ==> si on veut toujours créer une nouvelle entité avec un nouvel ID
                null,

                // Cela signifie que chaque fois que cette méthode est appelée, une nouvelle entité sans ID (ou avec un ID généré automatiquement par la base de données) sera créée
                //          newPorteurDto.getIdUtilisateur(),
                //L’ID de l’entité Porteur est pris du DTO newPorteurDto.
                // Cela permet de conserver l’ID existant de l’utilisateur si le DTO en contient un,
                // ce qui peut être utile pour des opérations de mise à jour où l’ID doit être préservé.
                // ==> si tu veux conserver l’ID de l’entité existante pour des opérations de mise à jour
                newPorteurDto.getNomUtilisateur(),
                newPorteurDto.getPrenomUtilisateur(),
                newPorteurDto.getEntrepriseUtilisateur(),
                newPorteurDto.getPlateformeUtilisateur(),
                newPorteurDto.getCodeUtilisateur(),
                newPorteurDto.getDateLancement(),
                newPorteurDto.getDomaine(),
                newPorteurDto.getBesoins(),
                newPorteurDto.getLieuActivite(),
                newPorteurDto.getDisponibilites()
              );
        return nouveauPorteurEntity;
    }


    public Porteur completerComptePorteur(Long idUtilisateur, String dateLancement, String domaine, String besoins, String lieuActivite, String disponibilites) {
        // Récupérer le parrain par son ID
        Porteur porteur = porteurRepository.findById(idUtilisateur)
                .orElseThrow(() -> new RuntimeException("Porteur non trouvé"));

        // Mettre à jour les champs du porteur
        porteur.setDateLancement(dateLancement);
        porteur.setDomaine(domaine);
        porteur.setBesoins(besoins);
        porteur.setLieuActivite(lieuActivite);
        porteur.setDisponibilites(disponibilites);

        // Sauvegarder le porteur mis à jour
        return porteurRepository.save(porteur);
    }
}
