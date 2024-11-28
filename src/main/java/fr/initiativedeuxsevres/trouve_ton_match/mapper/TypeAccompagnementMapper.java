package fr.initiativedeuxsevres.trouve_ton_match.mapper;

import fr.initiativedeuxsevres.trouve_ton_match.dto.TypeAccompagnementDto;
import fr.initiativedeuxsevres.trouve_ton_match.entity.TypeAccompagnement;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TypeAccompagnementMapper {

    private final UtilisateurMapper utilisateurMapper;

    // Injection du mapper des utilisateurs
    public TypeAccompagnementMapper(UtilisateurMapper utilisateurMapper) {
        this.utilisateurMapper = utilisateurMapper;
    }

    /**
     * Convertit une entité TypeAccompagnement en DTO TypeAccompagnementDto.
     *
     * @param entity L'entité TypeAccompagnement à convertir.
     * @return Un DTO TypeAccompagnementDto.
     */
    public TypeAccompagnementDto toDto(TypeAccompagnement entity) {
        if (entity == null) {
            return null;
        }

        return new TypeAccompagnementDto(
                entity.getId(),
                entity.getLabel(),
                entity.getUtilisateurs() != null
                        ? entity.getUtilisateurs().stream()
                        .map(utilisateurMapper::toDto) // Utilisation du mapper injecté
                        .collect(Collectors.toList())
                        : null
        );
    }

    /**
     * Convertit un DTO TypeAccompagnementDto en entité TypeAccompagnement.
     *
     * @param dto Le DTO TypeAccompagnementDto à convertir.
     * @return Une entité TypeAccompagnement.
     */
    public TypeAccompagnement toEntity(TypeAccompagnementDto dto) {
        if (dto == null) {
            return null;
        }

        return new TypeAccompagnement(
                dto.getId(),
                dto.getLabel(),
                dto.getUtilisateurs() != null
                        ? dto.getUtilisateurs().stream()
                        .map(utilisateurDto -> utilisateurMapper.toEntity(utilisateurDto, null, null)) // Utilisation du mapper pour les utilisateurs avec les listes nulles
                        .collect(Collectors.toList())
                        : null
        );
    }
}
