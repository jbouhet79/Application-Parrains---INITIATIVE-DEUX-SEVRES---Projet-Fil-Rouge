package fr.initiativedeuxsevres.trouve_ton_match.service;

import fr.initiativedeuxsevres.trouve_ton_match.dto.ParrainDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.PorteurDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.UtilisateurDto;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Parrain;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Porteur;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Utilisateur;
import fr.initiativedeuxsevres.trouve_ton_match.repository.ParrainRepository;
import fr.initiativedeuxsevres.trouve_ton_match.repository.PorteurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;

@Service
@Transactional
public class UtilisateurService {

    @Autowired
    private ParrainService parrainService;
    @Autowired
    private PorteurService porteurService;

    @Autowired
    private ParrainRepository parrainRepository;
    @Autowired
    private PorteurRepository porteurRepository;

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

    // Méthode qui vérifie l'existence d'un utilisateur qui peut être un parrain ou un porteur
    public boolean existsByNomPrenomCode(String nom, String prenom, String code) {
        return parrainRepository.existsByNomUtilisateurAndPrenomUtilisateurAndCodeUtilisateur(nom, prenom, code) ||
                porteurRepository.existsByNomUtilisateurAndPrenomUtilisateurAndCodeUtilisateur(nom, prenom, code);
    }

    public Utilisateur save(Utilisateur utilisateur) {
        if(utilisateur instanceof Parrain) {
            return parrainRepository.save((Parrain)utilisateur);
        } else {
            return porteurRepository.save((Porteur) utilisateur);
        }
    }

    public Utilisateur save(UtilisateurDto utilisateurDto) {
        if(utilisateurDto instanceof ParrainDto) {
            return parrainService.createParrain((ParrainDto)utilisateurDto);
        } else {
            return porteurService.createPorteur((PorteurDto)utilisateurDto);
        }
    }
}
