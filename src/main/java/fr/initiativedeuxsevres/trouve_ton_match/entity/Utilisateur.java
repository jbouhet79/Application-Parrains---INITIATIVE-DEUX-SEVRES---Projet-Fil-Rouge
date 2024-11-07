package fr.initiativedeuxsevres.trouve_ton_match.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "utilisateur")
public abstract class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idUtilisateur;

    protected String nomUtilisateur;
    protected String prenomUtilisateur;
    protected String entrepriseUtilisateur;
    protected String plateformeUtilisateur;
    protected String codeUtilisateur;

    @ManyToMany
    @JoinTable(name = "accompagnements_utilisateurs",
            joinColumns = @JoinColumn(name = "utilisateur"),
            inverseJoinColumns = @JoinColumn(name = "accompagnement"))
    protected List<TypeAccompagnement> accompagnementTypeList;

    // Constructeur avec tous les champs
    public Utilisateur(Long idUtilisateur, String nomUtilisateur, String prenomUtilisateur,
                       String entrepriseUtilisateur, String plateformeUtilisateur, String codeUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.entrepriseUtilisateur = entrepriseUtilisateur;
        this.plateformeUtilisateur = plateformeUtilisateur;
        this.codeUtilisateur = codeUtilisateur;
    }

}