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

    public ParrainDto createParrain (ParrainDto newParrainDto) {

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

        Parrain saved = parrainRepository.save(nouveauParrainEntity);

        // On transforme l'entité sauvée en un nouveau Dto pour le renvoyer au front (via le controlleur)
        return new ParrainDto(
                saved.getIdUtilisateur(),
                saved.getNomUtilisateur(),
                saved.getPrenomUtilisateur(),
                saved.getEntrepriseUtilisateur(),
                saved.getPlateformeUtilisateur(),
                saved.getCodeUtilisateur(),
                saved.getPresentationParcours(),
                saved.getBranchesReseau(),
                saved.getDomainesExpertise(),
                saved.getSecteurGeographique(),
                saved.getDisponibilites()
        );
    }
}
