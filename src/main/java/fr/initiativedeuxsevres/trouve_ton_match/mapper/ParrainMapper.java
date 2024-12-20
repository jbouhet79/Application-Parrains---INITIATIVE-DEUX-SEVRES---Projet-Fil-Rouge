package fr.initiativedeuxsevres.trouve_ton_match.mapper;

import fr.initiativedeuxsevres.trouve_ton_match.dto.ParrainDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.UtilisateurDto;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Parrain;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Utilisateur;


public class ParrainMapper extends UtilisateurMapper {


    /**
     * Convertit une entité Parrain en un DTO ParrainDto.
     *
     * @param entity L'entité Parrain à convertir.
     * @return Un DTO ParrainDto.
     */
    private ParrainDto toParrainDto(Parrain entity) {
        if (entity == null) {
            return null;
        }

        // Convertir l'entité en DTO
        ParrainDto dto = (ParrainDto) super.toDto(entity);

        // Mapper les propriétés spécifiques à Parrain
        dto.setPresentationParcours(entity.getPresentationParcours());
        dto.setBranchesReseau(entity.getBranchesReseau());
        dto.setDomainesExpertise(entity.getDomainesExpertise());
        dto.setSecteurGeographique(entity.getSecteurGeographique());
        dto.setDisponibilites(entity.getDisponibilites());

        return dto;
    }

    @Override
    public UtilisateurDto toDto(Utilisateur parrain) {
        return this.toParrainDto((Parrain) parrain);
    }

    public Utilisateur toEntity(UtilisateurDto parrainDto) {
        return this.toParrainEntity((ParrainDto) parrainDto);
    }


    /**
     * Convertit un DTO ParrainDto en une entité Parrain.
     *
     * @param parrainDto Le DTO à convertir.
     * @return Une entité Parrain.
     */
    private Parrain toParrainEntity(ParrainDto parrainDto) {
        if (parrainDto == null) {
            return null;
        }

        // Mapper les propriétés héritées de la classe Utilisateur
        Parrain parrain = (Parrain) super.toEntity(parrainDto);

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