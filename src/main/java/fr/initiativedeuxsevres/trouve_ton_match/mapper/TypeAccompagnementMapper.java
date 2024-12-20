package fr.initiativedeuxsevres.trouve_ton_match.mapper;

import fr.initiativedeuxsevres.trouve_ton_match.dto.TypeAccompagnementDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.UtilisateurDto;
import fr.initiativedeuxsevres.trouve_ton_match.entity.TypeAccompagnement;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TypeAccompagnementMapper {


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


        TypeAccompagnementDto typeAccompagnementDto = new TypeAccompagnementDto(
                entity.getId(),
                entity.getLabel());

        // Version détaillée
        /*if (entity.getUtilisateurs() != null) {
            List<UtilisateurDto> utilisateursDto = entity.getUtilisateurs().stream()
                    .map(utilisateur -> new UtilisateurMapper().toDto(utilisateur))
                    .collect(Collectors.toList());

            typeAccompagnementDto.setUtilisateurs(utilisateursDto);
        }*/

        return typeAccompagnementDto;
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
                null
                /*dto.getUtilisateurs() != null
                        ? dto.getUtilisateurs().stream()
                        .map(utilisateurDto -> new UtilisateurMapper().toEntity(utilisateurDto)) // Utilisation du mapper pour les utilisateurs avec les listes nulles
                        .collect(Collectors.toList())
                        : null*/
        );
    }

    public List<TypeAccompagnement> toEntityList(List<TypeAccompagnementDto> typeAccompagnementDtos) {

        if (typeAccompagnementDtos == null) {
            return Collections.emptyList();
        }

        return typeAccompagnementDtos.stream()// le Stream parcourt la liste
                .map(this::toEntity)  // le map appelle la méthode toEntity sur chaque élément de la liste
                .collect(Collectors.toList()); // le collect insère le résultat de la méthode toEntity dans une nouvelle liste
    }


    public List<TypeAccompagnementDto> toDtoList(List<TypeAccompagnement> listAccom) {

        if (listAccom == null) {
            return Collections.emptyList();
        }

        return listAccom.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}