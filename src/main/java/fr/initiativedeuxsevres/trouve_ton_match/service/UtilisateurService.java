package fr.initiativedeuxsevres.trouve_ton_match.service;

import fr.initiativedeuxsevres.trouve_ton_match.dto.ParrainDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.PorteurDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.UtilisateurDto;
import fr.initiativedeuxsevres.trouve_ton_match.entity.*;
import fr.initiativedeuxsevres.trouve_ton_match.mapper.ParrainMapper;
import fr.initiativedeuxsevres.trouve_ton_match.mapper.PorteurMapper;
import fr.initiativedeuxsevres.trouve_ton_match.mapper.UtilisateurMapper;
import fr.initiativedeuxsevres.trouve_ton_match.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor // Cette annotation génère automatiquement un constructeur pour tous les champs
// final ou marqués comme @NonNull
@Slf4j
public class UtilisateurService {


    private final ParrainRepository parrainRepository;
    private final PorteurRepository porteurRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final SecteurReseauRepository secteurReseauRepository;
    private final TypeAccompagnementRepository typeAccompagnementRepository;

    // Méthode pour trouver un utilisateur par ID
    public UtilisateurDto findById(Long idUtilisateur) {

        Utilisateur entity = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(
                        () -> new EntityNotFoundException("Utilisateur avec ID " + idUtilisateur + " non trouvé."));

        return UtilisateurMapper.toUtilisateurDto(entity);
    }

    // La méthode findByCodeUtilisateur recherche d’abord un Parrain par
    // codeUtilisateur.
    // Si aucun Parrain n’est trouvé, elle recherche un Porteur. Si aucun
    // utilisateur n’est trouvé, elle retourne null.
    public Utilisateur findByCodeUtilisateur(String codeUtilisateur) {
        Parrain parrain = parrainRepository.findByCodeUtilisateur(codeUtilisateur);
        if (parrain != null) {
            return parrain;
        }

        Porteur porteur = porteurRepository.findByCodeUtilisateur(codeUtilisateur);
        if (porteur != null) {
            return porteur;
        }

        return null;
    }

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

        // avec le Mapper
        System.out.println("Type d'utilisateur dans le service: " + utilisateurDto.getTypeUtilisateur());

        // Sauvegarder l'utilisateur
        if (utilisateurDto instanceof ParrainDto || utilisateurDto.getTypeUtilisateur().equals("parrain")) {
            Parrain parrain = (Parrain) UtilisateurMapper.toUtilisateurEntity(utilisateurDto);
            return parrainRepository.save(parrain);
        } else {
            Porteur porteur = (Porteur) UtilisateurMapper.toUtilisateurEntity(utilisateurDto);
            return porteurRepository.save(porteur);
        }
    }


    public UtilisateurDto selectionFiltres(UtilisateurDto utilisateurDto) {

        System.out.println("selectionFiltres - UtilisateurDto du front: " + utilisateurDto);
        System.out.println("Secteurs reçus : " + utilisateurDto.getSecteurReseauList());
        System.out.println("Accompagnements reçus : " + utilisateurDto.getAccompagnementTypeList());
        System.out.println("id ******************************* : " + utilisateurDto.getIdUtilisateur());

        // 1. Récupérer l'utilisateur par son ID
        UtilisateurDto utilisateurDtoFromBdd = findById(utilisateurDto.getIdUtilisateur());


        // 2. Mettre à jour les autres listes
        utilisateurDtoFromBdd.setSecteurReseauList(utilisateurDto.getSecteurReseauList());
        utilisateurDtoFromBdd.setAccompagnementTypeList(utilisateurDto.getAccompagnementTypeList());

        // 3. Mapper le DTO mis à jour en entité
        Utilisateur utilisateur = UtilisateurMapper.toUtilisateurEntity(utilisateurDtoFromBdd);

        System.out.println("selectionFiltres - Utilisateur récupéré: zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz" + utilisateur);
        if (utilisateur == null) {
            throw new EntityNotFoundException("Utilisateur non trouvé"); // Ou une autre exception personnalisée
        }

        // 4. Vérification des listes d'IDs
        if (utilisateurDto.getAccompagnementTypeList() == null || utilisateurDto.getSecteurReseauList() == null) {
            throw new IllegalArgumentException("Les listes d'IDs ne peuvent pas être nulles");
        }

        // 5. Récupérer dans des listes (accompagnements et secteurs), les types d'accompagnement et secteurs par leurs IDs
        List<SecteurReseau> secteurs = utilisateurDto.getSecteurReseauList().stream().map(dto -> secteurReseauRepository.findById(dto.getId())).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        List<TypeAccompagnement> accompagnements = utilisateurDto
                .getAccompagnementTypeList()
                .stream()
                .map(dto -> typeAccompagnementRepository.findById(dto.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

// version détaillé
//        // Initialisation de la liste des accompagnements
//        List<TypeAccompagnement> accompagnements = new ArrayList<>();
//
//        // Récupération de la liste des DTOs
//        List<AccompagnementTypeDto> dtoList = utilisateurDto.getAccompagnementTypeList();
//
//        // Parcours de chaque DTO dans la liste
//        for (AccompagnementTypeDto dto : dtoList) {
//            // Recherche du TypeAccompagnement par ID
//            Optional<TypeAccompagnement> optionalAccompagnement = typeAccompagnementRepository.findById(dto.getId());
//
//            // Vérification si le TypeAccompagnement est présent
//            if (optionalAccompagnement.isPresent()) {
//                // Ajout du TypeAccompagnement à la liste des accompagnements
//                accompagnements.add(optionalAccompagnement.get());
//            }
//        }

        // Convertir les entités en DTOs
//        List<TypeAccompagnementDto> accompagnementDtos = accompagnements.stream()
//                .map(typeAccompagnementMapper::toDto)
//                .collect(Collectors.toList());
//
//        List<SecteurReseauDto> secteurDtos = secteurs.stream()
//                .map(secteurReseauMapper::toDto)
//                .collect(Collectors.toList());

        // 4. Associer les entités à l'utilisateur
//        if (accompagnements != null && !accompagnements.isEmpty()) { // vérifie si la liste "accompagnements" n'est pas vide
//            List<Long> idAccompagnements = new ArrayList<Long>(); // Constitue avec la boucle qui suit la liste des Ids
//            for(TypeAccompagnement accomp : accompagnements)
//            {
//                if(accomp.isPresent()){
//                    idAccompagnements.add(accomp.get().getId());
//                }
//
//            }
        utilisateur.setAccompagnementTypeList(accompagnements); // mise à jour de la propriété AccompagnementTypeList de utilisateur avec la liste des accompagnements récupérés
//        }

//        if (secteurs != null && !secteurs.isEmpty()) {
//            List<Long> idSecteurs = new ArrayList<Long>();
//            for(SecteurReseau sect : secteurs)
//            {
//                idSecteurs.add(sect.getId());
//            }
        utilisateur.setSecteurReseauList(secteurs);
//        }

        // **4. Mise à jour des relations de l'utilisateur**
//        utilisateur.getSecteurReseauList().clear();
//        utilisateur.getSecteurReseauList().addAll(secteurs);
//
//        utilisateur.getAccompagnementTypeList().clear();
//        utilisateur.getAccompagnementTypeList().addAll(accompagnements);

        // 5. Sauvegarder les modifications de l'utilisateur
        Utilisateur updatedUtilisateur = save(utilisateur);
        log.warn("selectionFiltres - Utilisateur récupéré: " + updatedUtilisateur);
        System.out.println("selectionFiltres - updatedUtilisateur: " + updatedUtilisateur);

        // 6. Convertir l'entité sauvegardée en DTO

        UtilisateurDto updatedUtilisateurDto = UtilisateurMapper.toUtilisateurDto(updatedUtilisateur);


        log.warn("selectionFiltres - Utilisateur récupéré: " + updatedUtilisateurDto);
        System.out.println("selectionFiltres - updatedUtilisateurDto: " + updatedUtilisateurDto);

        return updatedUtilisateurDto;


        // version hybride Mapper

//        // 1. Récupérer l'utilisateur par son ID
//        UtilisateurDto utilisateur = findById(utilisateurDto.getIdUtilisateur());
//        if (utilisateur == null) {
//            throw new EntityNotFoundException("Utilisateur non trouvé"); // Ou une autre exception personnalisée
//        }
//
//        // 2. Vérification des listes d'IDs
//        if (utilisateurDto.getAccompagnementTypeList() == null || utilisateurDto.getSecteurReseauList() == null) {
//            throw new IllegalArgumentException("Les listes d'IDs ne peuvent pas être nulles");
//        }
//
//        // 3. Récupérer dans des listes (accompagnements et secteurs), les types d'accompagnement et secteurs par leurs IDs
//        List<TypeAccompagnement> accompagnements = typeAccompagnementService
//                .findAllById(utilisateurDto.getAccompagnementTypeList());
//        List<SecteurReseau> secteurs = secteurReseauService.findAllById(utilisateurDto.getSecteurReseauList());
//
//        // Convertir les entités en DTOs
//        List<TypeAccompagnementDto> accompagnementDtos = accompagnements.stream()
//                .map(typeAccompagnementMapper::toDto)
//                .collect(Collectors.toList());
//
//        List<SecteurReseauDto> secteurDtos = secteurs.stream()
//                .map(secteurReseauMapper::toDto)
//                .collect(Collectors.toList());
//
//        // 4. Associer les entités à l'utilisateur
//        if (accompagnements != null && !accompagnements.isEmpty()) { // vérifie si la liste "accompagnements" n'est pas vide
//            List<Long> idAccompagnements = new ArrayList<Long>(); // Constitue avec la boucle qui suit la liste des Ids
//            for(TypeAccompagnement accomp : accompagnements)
//            {
//                idAccompagnements.add(accomp.getId());
//            }
//            utilisateur.setAccompagnementTypeList(idAccompagnements); // mise à jour de la propriété AccompagnementTypeList de utilisateur avec la liste des accompagnements récupérés
//        }
//
//        if (secteurs != null && !secteurs.isEmpty()) {
//            List<Long> idSecteurs = new ArrayList<Long>();
//            for(SecteurReseau sect : secteurs)
//            {
//                idSecteurs.add(sect.getId());
//            }
//            utilisateur.setSecteurReseauList(idSecteurs);
//        }
//
//        // 5. Sauvegarder les modifications de l'utilisateur
//        // return save(utilisateur);
//
//        // UtilisateurMapper::toEntity attend des DTOs
//        Utilisateur utilisateurEntity = utilisateurMapper.toEntity(utilisateur, accompagnementDtos, secteurDtos);
//        Utilisateur savedUtilisateur = save(utilisateurEntity);
//
//        // 6. Convertir l'entité sauvegardée en DTO
//        UtilisateurDto savedUtilisateurDto = utilisateurMapper.toDto(savedUtilisateur);
//
//        return savedUtilisateurDto;
    }

}
