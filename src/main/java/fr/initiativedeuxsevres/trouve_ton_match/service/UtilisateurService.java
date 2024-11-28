package fr.initiativedeuxsevres.trouve_ton_match.service;

import fr.initiativedeuxsevres.trouve_ton_match.dto.*;
import fr.initiativedeuxsevres.trouve_ton_match.mapper.UtilisateurMapper;
import fr.initiativedeuxsevres.trouve_ton_match.entity.*;
import fr.initiativedeuxsevres.trouve_ton_match.repository.ParrainRepository;
import fr.initiativedeuxsevres.trouve_ton_match.repository.PorteurRepository;
import fr.initiativedeuxsevres.trouve_ton_match.repository.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor // Cette annotation génère automatiquement un constructeur pour tous les champs
                         // final ou marqués comme @NonNull
public class UtilisateurService {

    private final ParrainService parrainService;
    private final PorteurService porteurService;
    private final SecteurReseauService secteurReseauService;
    private final TypeAccompagnementService typeAccompagnementService;

    private final UtilisateurMapper utilisateurMapper;

    private final ParrainRepository parrainRepository;
    private final PorteurRepository porteurRepository;
    private final UtilisateurRepository utilisateurRepository;

    // Méthode pour trouver un utilisateur par ID
    public Utilisateur findById(Long idUtilisateur) {
        return utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(
                        () -> new EntityNotFoundException("Utilisateur avec ID " + idUtilisateur + " non trouvé."));
    }

    // La méthode findByCodeUtilisateur recherche d’abord un Parrain par
    // codeUtilisateur.
    // Si aucun Parrain n’est trouvé, elle recherche un Porteur. Si aucun
    // utilisateur n’est trouvé, elle retourne null.
    public Utilisateur findByCodeUtilisateur(String codeUtilisateur) {
        Parrain parrain = parrainRepository.findByCodeUtilisateur(codeUtilisateur);
        if (parrain != null) {
            return  parrain;
        }

        Porteur porteur = porteurRepository.findByCodeUtilisateur(codeUtilisateur);
        if (porteur != null) {
            return porteur;
        }

        return null;
    }

//    public Utilisateur findByCodeUtilisateur(String codeUtilisateur) {
//        Parrain parrain = parrainRepository.findByCodeUtilisateur(codeUtilisateur);
//        if (parrain != null) {
//            return  new ParrainDto(parrain);
//        }
//
//        Porteur porteur = porteurRepository.findByCodeUtilisateur(codeUtilisateur);
//        if (porteur != null) {
//            return new PorteurDto(porteur);
//        }
//
//        return null;
//    }

    // Méthode qui renvoie un utilisateur qui peut être un parrain ou un porteur
    public Optional<Utilisateur> getByNomPrenomCode(String nom, String prenom, String code) {
        // Essayez de trouver l'utilisateur dans le repository parrain
        Optional<Parrain> parrain = parrainRepository.findByNomUtilisateurAndPrenomUtilisateurAndCodeUtilisateur(nom,
                prenom, code);

        // Si trouvé dans parrain, renvoie-le
        if (parrain.isPresent()) {
            return Optional.of(parrain.get());
        }

        Optional<Porteur> porteur = porteurRepository.findByNomUtilisateurAndPrenomUtilisateurAndCodeUtilisateur(nom,
                prenom, code);

        if (porteur.isPresent()) {
            return Optional.of(porteur.get());
        }

        // Sinon, essayez de trouver l'utilisateur dans le repository porteur
        return Optional.empty();
    }

    public Utilisateur save(Utilisateur utilisateur) {
        if (utilisateur instanceof Parrain) {
            return parrainRepository.save((Parrain) utilisateur);
        } else {
            return porteurRepository.save((Porteur) utilisateur);
        }
    }

    public Utilisateur save(UtilisateurDto utilisateurDto) {

        // avant le Mapper
//        System.out.println("Type d'utilisateur: " + utilisateurDto.getTypeUtilisateur());
//         if(utilisateurDto.getTypeUtilisateur().equals("parrain")) {
////        if (utilisateurDto.getType() != null && utilisateurDto.getType().name().toLowerCase().equals("parrain")) {
//            return parrainService.createParrain(utilisateurDto);
//        } else {
//            return porteurService.createPorteur(utilisateurDto);
//        }

         // avec le Mapper
        System.out.println("Type d'utilisateur dans le service: " + utilisateurDto.getTypeUtilisateur());

        // Mapper pour convertir le DTO en entité
        List<TypeAccompagnementDto> accompagnements = typeAccompagnementService.findAll(); // Récupérer ou créer la liste
        List<SecteurReseauDto> secteursReseaux = secteurReseauService.findAll(); // Récupérer ou créer la liste
        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDto, accompagnements, secteursReseaux);

        // Sauvegarder l'utilisateur
        if (utilisateur instanceof Parrain) {
            return parrainRepository.save((Parrain) utilisateur);
        } else {
            return porteurRepository.save((Porteur) utilisateur);
        }
    }

//    public Utilisateur mettreAJourFiltres(UtilisateurDto utilisateurDto) {
//        Utilisateur utilisateur = findByCodeUtilisateur(utilisateurDto.getCodeUtilisateur());
//
//        // Mettre à jour les accompagnements
//        if (utilisateurDto.getAccompagnementTypeList() != null) {
//            List<TypeAccompagnement> accompagnements = typeAccompagnementService
//                    .findAllById(utilisateurDto.getAccompagnementTypeList());
//            utilisateur.setAccompagnementTypeList(accompagnements);
//        }
//
//        // Mettre à jour les secteurs/réseaux
//        if (utilisateurDto.getSecteurReseauList() != null) {
//            List<SecteurReseau> secteurs = secteurReseauService.findAllById(utilisateurDto.getSecteurReseauList());
//            utilisateur.setSecteurReseauList(secteurs);
//        }
//
//        return save(utilisateur);
//    }

    public Utilisateur selectionFiltres(UtilisateurDto utilisateurDto) {
        // 1. Récupérer l'utilisateur par son ID
        Utilisateur utilisateur = findById(utilisateurDto.getIdUtilisateur());
        if (utilisateur == null) {
            throw new EntityNotFoundException("Utilisateur non trouvé"); // Ou une autre exception personnalisée
        }

        // 3. Vérification des listes d'IDs
        if (utilisateurDto.getAccompagnementTypeList() == null || utilisateurDto.getSecteurReseauList() == null) {
            throw new IllegalArgumentException("Les listes d'IDs ne peuvent pas être nulles");
        }

        // 4. Récupérer les types d'accompagnement et secteurs par leurs IDs
        List<TypeAccompagnement> accompagnements = typeAccompagnementService
                .findAllById(utilisateurDto.getAccompagnementTypeList());
        List<SecteurReseau> secteurs = secteurReseauService.findAllById(utilisateurDto.getSecteurReseauList());

        // 7. Associer les entités à l'utilisateur
        utilisateur.setAccompagnementTypeList(accompagnements);
        utilisateur.setSecteurReseauList(secteurs);

        // 8. Sauvegarder les modifications de l'utilisateur
        return save(utilisateur);
    }

}
