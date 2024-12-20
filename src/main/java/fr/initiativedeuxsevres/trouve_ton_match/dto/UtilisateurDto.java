package fr.initiativedeuxsevres.trouve_ton_match.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.util.List;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, // Utilise un champ "type" pour identifier la sous-classe
        include = JsonTypeInfo.As.PROPERTY, // Le champ "type" est une propriété dans le JSON
        property = "typeUtilisateur" // Le nom du champ discriminant dans le JSON
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ParrainDto.class, name = "parrain"),
        @JsonSubTypes.Type(value = PorteurDto.class, name = "porteur")
})
@Data
public abstract class UtilisateurDto {

    /**
     * Propriétés protected pour que les classes héritants de UtilisateurDto
     * puissent les utiliser
     */
    protected Long idUtilisateur;
    protected String nomUtilisateur;
    protected String prenomUtilisateur;
    protected String entrepriseUtilisateur;
    protected String plateformeUtilisateur;
    protected String codeUtilisateur;

    protected String typeUtilisateur;
    protected List<SecteurReseauDto> secteurReseauList;  // Liste de DTO pour les secteurs de réseau
    protected List<TypeAccompagnementDto> accompagnementTypeList;  // Liste de DTO pour les types d'accompagnement


}
