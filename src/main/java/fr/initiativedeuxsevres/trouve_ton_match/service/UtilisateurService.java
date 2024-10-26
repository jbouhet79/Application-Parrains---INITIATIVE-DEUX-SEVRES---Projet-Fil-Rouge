package fr.initiativedeuxsevres.trouve_ton_match.service;

import fr.initiativedeuxsevres.trouve_ton_match.dto.ParrainDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.PorteurDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.UtilisateurDto;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Parrain;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Porteur;
import fr.initiativedeuxsevres.trouve_ton_match.repository.ParrainRepository;
import fr.initiativedeuxsevres.trouve_ton_match.repository.PorteurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UtilisateurService {

    @Autowired
    private ParrainRepository parrainRepository;

    @Autowired
    private PorteurRepository porteurRepository;

    // La méthode findByCodeUtilisateur recherche d’abord un Parrain par codeUtilisateur. 
    // Si aucun Parrain n’est trouvé, elle recherche un Porteur. Si aucun utilisateur n’est trouvé, elle retourne null.
    public UtilisateurDto findByCodeUtilisateur(String codeUtilisateur) {
        Parrain parrain = parrainRepository.findByCodeUtilisateur(codeUtilisateur);
        if (parrain != null) {
            return new ParrainDto(parrain);
        }

        Porteur porteur = porteurRepository.findByCodeUtilisateur(codeUtilisateur);
        if (porteur != null) {
            return new PorteurDto(porteur);
        }

        return null;
    }

    // Méthode qui vérifie l'existence d'un utilisateur qui peut être un parrain ou un porteur
    public boolean existsByNomPrenomCode(String nom, String prenom, String code) {
        return parrainRepository.existsByNomUtilisateurAndPrenomUtilisateurAndCodeUtilisateur(nom, prenom, code) ||
                porteurRepository.existsByNomUtilisateurAndPrenomUtilisateurAndCodeUtilisateur(nom, prenom, code);
    }
}
