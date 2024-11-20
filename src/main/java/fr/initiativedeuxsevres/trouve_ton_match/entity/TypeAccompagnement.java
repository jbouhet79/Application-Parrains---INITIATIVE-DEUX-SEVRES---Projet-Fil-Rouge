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
public class TypeAccompagnement {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "accompagnement")
    // private List<Long> name;
     private List<String> name;
    // private String name;

    @ManyToMany(mappedBy = "accompagnementTypeList")
    private List<Utilisateur> utilisateurs;

}
