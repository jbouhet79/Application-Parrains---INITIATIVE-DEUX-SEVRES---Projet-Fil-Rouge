package fr.initiativedeuxsevres.trouve_ton_match.service;

import fr.initiativedeuxsevres.trouve_ton_match.dto.ParrainDto;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Parrain;
import fr.initiativedeuxsevres.trouve_ton_match.repository.ParrainRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ParrainService {

    @Autowired
    private ParrainRepository parrainRepository;

    public Parrain createParrain (ParrainDto newParrainDto) {

        // On transforme un Dto en entité
        Parrain nouveauParrainEntity = new Parrain(
                null,
                newParrainDto.getNomUtilisateur(),
                newParrainDto.getPrenomUtilisateur(),
                newParrainDto.getEntrepriseUtilisateur(),
                newParrainDto.getPlateformeUtilisateur(),
                newParrainDto.getCodeUtilisateur(),
                newParrainDto.getPresentationParcours(),
                newParrainDto.getBranchesReseau(),
                newParrainDto.getDomainesExpertise(),
                newParrainDto.getSecteurGeographique(),
                newParrainDto.getDisponibilites()
        );

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
}
