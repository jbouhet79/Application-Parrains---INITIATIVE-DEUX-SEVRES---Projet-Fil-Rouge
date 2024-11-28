package fr.initiativedeuxsevres.trouve_ton_match.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeAccompagnementDto {

    private Long id;
    private String label;
    private List<UtilisateurDto> utilisateurs;

    public TypeAccompagnementDto(Long id, String label) {
        this.id = id;
        this.label = label;
    }
}
