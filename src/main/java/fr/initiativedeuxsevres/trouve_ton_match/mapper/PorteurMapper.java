package fr.initiativedeuxsevres.trouve_ton_match.mapper;

import fr.initiativedeuxsevres.trouve_ton_match.dto.PorteurDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.UtilisateurDto;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Porteur;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Utilisateur;


public class PorteurMapper extends UtilisateurMapper {


    /**
     * Convertit une entité Porteur en DTO PorteurDto.
     *
     * @param entity L'entité Porteur à convertir.
     * @return Le DTO PorteurDto.
     */
    private PorteurDto toPorteurDto(Porteur entity) {
        if (entity == null) {
            return null;
        }

        // Conversion de l'entité Porteur en DTO PorteurDto
        PorteurDto dto = (PorteurDto) super.toDto(entity);

        dto.setDateLancement(entity.getDateLancement());
        dto.setDomaine(entity.getDomaine());
        dto.setDescriptifActivite(entity.getDescriptifActivite());
        dto.setBesoins(entity.getBesoins());
        dto.setLieuActivite(entity.getLieuActivite());
        dto.setDisponibilites(entity.getDisponibilites());

        return dto;
    }

    @Override
    public UtilisateurDto toDto(Utilisateur entity) {
        return this.toPorteurDto((Porteur) entity);
    }


    public Utilisateur toEntity(UtilisateurDto porteurDto) {
        return this.toPorteurEntity((PorteurDto) porteurDto);
    }

    /**
     * Convertit un DTO PorteurDto en entité Porteur.
     *
     * @param porteurDto Le DTO PorteurDto à convertir.
     * @return L'entité Porteur.
     */
    private Porteur toPorteurEntity(PorteurDto porteurDto) {
        if (porteurDto == null) {
            return null;
        }

        // on doit caster l'utilisateur en Porteur car on ne peut pas déduire l'enfant depuis le parent
        // On n'a pas besoin de caster le porteurDto en utilisateurDto car on connait toujours le parent d'un enfant.
        Porteur porteur = (Porteur) super.toEntity(porteurDto);

        // Mapper les propriétés spécifiques à Parrain
        porteur.setDateLancement(porteurDto.getDateLancement());
        porteur.setDomaine(porteurDto.getDomaine());
        porteur.setBesoins(porteurDto.getBesoins());
        porteur.setLieuActivite(porteurDto.getLieuActivite());
        porteur.setDisponibilites(porteurDto.getDisponibilites());

        return porteur;
    }
}