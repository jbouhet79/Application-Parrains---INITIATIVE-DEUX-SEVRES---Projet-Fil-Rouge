package fr.initiativedeuxsevres.trouve_ton_match.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class SecteurReseau {

    @Id
    private Long id;

    private String label;

    @ManyToMany(mappedBy = "secteurReseauList")
    private List<Utilisateur> utilisateurs;
}
