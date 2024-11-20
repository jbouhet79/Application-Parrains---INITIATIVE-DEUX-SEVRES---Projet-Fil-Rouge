package fr.initiativedeuxsevres.trouve_ton_match.service;

import fr.initiativedeuxsevres.trouve_ton_match.dto.UtilisateurDto;
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
@RequiredArgsConstructor
public class UtilisateurService {

    @Autowired
    private ParrainService parrainService;
    @Autowired
    private PorteurService porteurService;

    @Autowired
    private ParrainRepository parrainRepository;
    @Autowired
    private PorteurRepository porteurRepository;

    private final UtilisateurRepository utilisateurRepository;

    private final SecteursReseauxService secteursReseauxService;
    private final TypeAccompagnementService typeAccompagnementService;

    // Injection du repository via le constructeur
//    @Autowired
//    public UtilisateurService(UtilisateurRepository utilisateurRepository, SecteursReseauxService secteursReseauxService, TypeAccompagnementService typeAccompagnementService) {
//        this.utilisateurRepository = utilisateurRepository;
//        this.secteursReseauxService = secteursReseauxService;
//        this.typeAccompagnementService = typeAccompagnementService;
//    }

    // Méthode pour trouver un utilisateur par ID
    public Utilisateur findById(Long idUtilisateur) {
        return utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur avec ID " + idUtilisateur + " non trouvé."));
    }


    // La méthode findByCodeUtilisateur recherche d’abord un Parrain par codeUtilisateur. 
    // Si aucun Parrain n’est trouvé, elle recherche un Porteur. Si aucun utilisateur n’est trouvé, elle retourne null.
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
        Optional<Parrain> parrain = parrainRepository.findByNomUtilisateurAndPrenomUtilisateurAndCodeUtilisateur(nom, prenom, code);

        // Si trouvé dans parrain, renvoie-le
        if (parrain.isPresent()) {
            return Optional.of(parrain.get());
        }

        Optional<Porteur> porteur = porteurRepository.findByNomUtilisateurAndPrenomUtilisateurAndCodeUtilisateur(nom, prenom, code);

        if (porteur.isPresent()) {
            return Optional.of(porteur.get());
        }

        // Sinon, essayez de trouver l'utilisateur dans le repository porteur
        return Optional.empty();
    }

    public Utilisateur save(Utilisateur utilisateur) {
        if(utilisateur instanceof Parrain) {
            return parrainRepository.save((Parrain)utilisateur);
        } else {
            return porteurRepository.save((Porteur) utilisateur);
        }
    }

    public Utilisateur save(UtilisateurDto utilisateurDto) {
        System.out.println("Type d'utilisateur: " + utilisateurDto.getType());
        // if(utilisateurDto.getType().equals("Parrain")) {
        if (utilisateurDto.getType() != null && utilisateurDto.getType().toLowerCase().equals("parrain")) {
            return parrainService.createParrain(utilisateurDto);
        } else {
            return porteurService.createPorteur(utilisateurDto);
        }
    }

    public Utilisateur mettreAJourFiltres(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = findByCodeUtilisateur(utilisateurDto.getCodeUtilisateur());

        // Mettre à jour les accompagnements
        if (utilisateurDto.getAccompagnementTypeList() != null) {
            List<TypeAccompagnement> accompagnements = typeAccompagnementService.findAllById(utilisateurDto.getAccompagnementTypeList());
            utilisateur.setAccompagnementTypeList(accompagnements);
        }

        // Mettre à jour les secteurs/réseaux
        if (utilisateurDto.getSecteursReseauxList() != null) {
            List<SecteursReseaux> secteurs = secteursReseauxService.findAllById(utilisateurDto.getSecteursReseauxList());
            utilisateur.setSecteursReseauxList(secteurs);
        }

        return save(utilisateur);
    }

}
