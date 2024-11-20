package fr.initiativedeuxsevres.trouve_ton_match.service;

import fr.initiativedeuxsevres.trouve_ton_match.dto.ParrainDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.UtilisateurDto;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Parrain;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Porteur;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Utilisateur;
import fr.initiativedeuxsevres.trouve_ton_match.repository.ParrainRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ParrainService {

    @Autowired
    private ParrainRepository parrainRepository;

    public Parrain createParrain (UtilisateurDto newParrainDto) {

        // On transforme un Dto en entité
        Parrain nouveauParrainEntity = Parrain.builder()
                .nomUtilisateur(newParrainDto.getNomUtilisateur())
                .prenomUtilisateur(newParrainDto.getPrenomUtilisateur())
                .entrepriseUtilisateur(newParrainDto.getEntrepriseUtilisateur())
                .plateformeUtilisateur(newParrainDto.getPlateformeUtilisateur())
                .codeUtilisateur(newParrainDto.getCodeUtilisateur())
                .build();

        return parrainRepository.save(nouveauParrainEntity);
    }

    public Parrain completerCompteParrain(Long idUtilisateur, String presentationParcours, String branchesReseau, String domainesExpertise, String secteurGeographique, String disponibilites) {
        // Récupérer le parrain par son ID
        Parrain parrain = parrainRepository.findById(idUtilisateur)
                .orElseThrow(() -> new RuntimeException("Parrain non trouvé"));

        // Mettre à jour les champs du parrain
        parrain.setPresentationParcours(presentationParcours);
        parrain.setBranchesReseau(branchesReseau);
        parrain.setDomainesExpertise(domainesExpertise);
        parrain.setSecteurGeographique(secteurGeographique);
        parrain.setDisponibilites(disponibilites);

        // Sauvegarder le parrain mis à jour
        return parrainRepository.save(parrain);
    }

    public Parrain findById(Long id) {
        Optional<Parrain> parrain = parrainRepository.findById(id);
        return parrain.isPresent() ? parrain.get() : null;
    }

    public Parrain save(Parrain parrain) {
        System.out.println("Sauvegarde en cours pour : " + parrain);
        return parrainRepository.save(parrain);
    }
}
