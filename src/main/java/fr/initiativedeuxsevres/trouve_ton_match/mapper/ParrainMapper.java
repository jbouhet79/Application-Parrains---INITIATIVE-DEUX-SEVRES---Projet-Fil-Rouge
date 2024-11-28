package fr.initiativedeuxsevres.trouve_ton_match.mapper;

import fr.initiativedeuxsevres.trouve_ton_match.dto.ParrainDto;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Parrain;
import org.springframework.stereotype.Component;

@Component
public class ParrainMapper {

    private final UtilisateurMapper utilisateurMapper;

    // Injection du mapper Utilisateur si nécessaire
    public ParrainMapper(UtilisateurMapper utilisateurMapper) {
        this.utilisateurMapper = utilisateurMapper;
    }

    /**
     * Convertit une entité Parrain en un DTO ParrainDto.
     * @param parrain L'entité Parrain à convertir.
     * @return Un DTO ParrainDto.
     */
    public ParrainDto toDto(Parrain parrain) {
        if (parrain == null) {
            return null;
        }

        // Convertir l'entité en DTO
        ParrainDto parrainDto = new ParrainDto();

        // Mapper les propriétés héritées de la classe Utilisateur
        parrainDto.setIdUtilisateur(parrain.getIdUtilisateur());
        parrainDto.setNomUtilisateur(parrain.getNomUtilisateur());
        parrainDto.setPrenomUtilisateur(parrain.getPrenomUtilisateur());
        parrainDto.setEntrepriseUtilisateur(parrain.getEntrepriseUtilisateur());
        parrainDto.setPlateformeUtilisateur(parrain.getPlateformeUtilisateur());
        parrainDto.setCodeUtilisateur(parrain.getCodeUtilisateur());

        // Mapper les propriétés spécifiques à Parrain
        parrainDto.setPresentationParcours(parrain.getPresentationParcours());
        parrainDto.setBranchesReseau(parrain.getBranchesReseau());
        parrainDto.setDomainesExpertise(parrain.getDomainesExpertise());
        parrainDto.setSecteurGeographique(parrain.getSecteurGeographique());
        parrainDto.setDisponibilites(parrain.getDisponibilites());

        return parrainDto;
    }

    /**
     * Convertit un DTO ParrainDto en une entité Parrain.
     * @param parrainDto Le DTO à convertir.
     * @return Une entité Parrain.
     */
    public Parrain toEntity(ParrainDto parrainDto) {
        if (parrainDto == null) {
            return null;
        }

        // Convertir le DTO en entité
        Parrain parrain = new Parrain();

        // Mapper les propriétés héritées de la classe Utilisateur
        parrain.setIdUtilisateur(parrainDto.getIdUtilisateur());
        parrain.setNomUtilisateur(parrainDto.getNomUtilisateur());
        parrain.setPrenomUtilisateur(parrainDto.getPrenomUtilisateur());
        parrain.setEntrepriseUtilisateur(parrainDto.getEntrepriseUtilisateur());
        parrain.setPlateformeUtilisateur(parrainDto.getPlateformeUtilisateur());
        parrain.setCodeUtilisateur(parrainDto.getCodeUtilisateur());
        parrain.setTypeUtilisateur(parrainDto.getTypeUtilisateur());
        System.out.println("Type d'utilisateur dans ParrainMapper toEntity: " + parrainDto.getTypeUtilisateur());
        System.out.println("Type d'utilisateur dans ParrainMapper toEntity parrain.getType(): " + parrain.getTypeUtilisateur());

        // Mapper les propriétés spécifiques à Parrain
        parrain.setPresentationParcours(parrainDto.getPresentationParcours());
        parrain.setBranchesReseau(parrainDto.getBranchesReseau());
        parrain.setDomainesExpertise(parrainDto.getDomainesExpertise());
        parrain.setSecteurGeographique(parrainDto.getSecteurGeographique());
        parrain.setDisponibilites(parrainDto.getDisponibilites());

        // Si nécessaire, vous pouvez aussi mapper des entités liées, comme des relations ManyToMany.

        return parrain;
    }
}