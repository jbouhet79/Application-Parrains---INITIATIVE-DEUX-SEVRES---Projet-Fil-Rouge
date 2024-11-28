package fr.initiativedeuxsevres.trouve_ton_match.mapper;

import fr.initiativedeuxsevres.trouve_ton_match.dto.PorteurDto;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Porteur;
import fr.initiativedeuxsevres.trouve_ton_match.mapper.UtilisateurMapper;
import org.springframework.stereotype.Component;

@Component
public class PorteurMapper {

    private final UtilisateurMapper utilisateurMapper;

    // Injection du mapper Utilisateur
    public PorteurMapper(UtilisateurMapper utilisateurMapper) {
        this.utilisateurMapper = utilisateurMapper;
    }

    /**
     * Convertit une entité Porteur en DTO PorteurDto.
     *
     * @param entity L'entité Porteur à convertir.
     * @return Le DTO PorteurDto.
     */
    public PorteurDto toDto(Porteur entity) {
        if (entity == null) {
            return null;
        }

        // Conversion de l'entité Porteur en DTO PorteurDto
        PorteurDto dto = new PorteurDto();
        dto.setIdUtilisateur(entity.getIdUtilisateur());
        dto.setNomUtilisateur(entity.getNomUtilisateur());
        dto.setPrenomUtilisateur(entity.getPrenomUtilisateur());
        dto.setEntrepriseUtilisateur(entity.getEntrepriseUtilisateur());
        dto.setPlateformeUtilisateur(entity.getPlateformeUtilisateur());
        dto.setCodeUtilisateur(entity.getCodeUtilisateur());
        dto.setDateLancement(entity.getDateLancement());
        dto.setDomaine(entity.getDomaine());
        dto.setBesoins(entity.getBesoins());
        dto.setLieuActivite(entity.getLieuActivite());
        dto.setDisponibilites(entity.getDisponibilites());

        return dto;
    }

    /**
     * Convertit un DTO PorteurDto en entité Porteur.
     *
     * @param dto Le DTO PorteurDto à convertir.
     * @return L'entité Porteur.
     */
    public Porteur toEntity(PorteurDto dto) {
        if (dto == null) {
            return null;
        }

        // Création de l'entité Porteur
        Porteur entity = new Porteur();
        entity.setIdUtilisateur(dto.getIdUtilisateur());
        entity.setNomUtilisateur(dto.getNomUtilisateur());
        entity.setPrenomUtilisateur(dto.getPrenomUtilisateur());
        entity.setEntrepriseUtilisateur(dto.getEntrepriseUtilisateur());
        entity.setPlateformeUtilisateur(dto.getPlateformeUtilisateur());
        entity.setCodeUtilisateur(dto.getCodeUtilisateur());
        entity.setTypeUtilisateur(dto.getTypeUtilisateur());
        entity.setDateLancement(dto.getDateLancement());
        entity.setDomaine(dto.getDomaine());
        entity.setBesoins(dto.getBesoins());
        entity.setLieuActivite(dto.getLieuActivite());
        entity.setDisponibilites(dto.getDisponibilites());

        return entity;
    }
}