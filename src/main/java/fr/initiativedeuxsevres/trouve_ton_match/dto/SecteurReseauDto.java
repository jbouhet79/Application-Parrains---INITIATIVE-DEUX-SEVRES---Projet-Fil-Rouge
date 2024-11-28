package fr.initiativedeuxsevres.trouve_ton_match.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor // Génère un constructeur avec tous les arguments.
@NoArgsConstructor // Génère un constructeur sans arguments.
public class SecteurReseauDto {

    private Long id;
    private String label;
    private List<UtilisateurDto> utilisateurs;

    // Constructeurs, getters et setters
    public SecteurReseauDto(Long id, String label) {
        this.id = id;
        this.label = label;
    }

}
