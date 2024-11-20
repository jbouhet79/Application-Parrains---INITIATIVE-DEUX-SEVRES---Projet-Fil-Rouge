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
public class SecteursReseaux {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "secteurs_reseaux")
    // private List<Long> name;
    private List<String> name;

    @ManyToMany(mappedBy = "secteursReseauxList")
    private List<Utilisateur> utilisateurs;
}
