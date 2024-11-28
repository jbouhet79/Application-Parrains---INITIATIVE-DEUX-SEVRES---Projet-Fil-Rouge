package fr.initiativedeuxsevres.trouve_ton_match.entity;

import fr.initiativedeuxsevres.trouve_ton_match.enums.TypeUtilisateur;
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
        protected String typeUtilisateur;

//        @Column(nullable = false)
//        @Enumerated(EnumType.STRING)
//        protected TypeUtilisateur type;

        @ManyToMany
        @JoinTable(name = "accompagnement_utilisateur", joinColumns = @JoinColumn(name = "id_utilisateur"), inverseJoinColumns = @JoinColumn(name = "id_accompagnement"))
        protected List<TypeAccompagnement> accompagnementTypeList;

        @ManyToMany
        @JoinTable(name = "secteur_reseau_utilisateur", joinColumns = @JoinColumn(name = "id_utilisateur"), inverseJoinColumns = @JoinColumn(name = "id_secteur_reseau"))
        protected List<SecteurReseau> secteurReseauList;

        public Utilisateur() {
        }

        // Constructeur avec tous les champs
        public Utilisateur(Long idUtilisateur, String nomUtilisateur, String prenomUtilisateur,
                        String entrepriseUtilisateur, String plateformeUtilisateur, String codeUtilisateur, String typeUtilisateur) {
                this.idUtilisateur = idUtilisateur;
                this.nomUtilisateur = nomUtilisateur;
                this.prenomUtilisateur = prenomUtilisateur;
                this.entrepriseUtilisateur = entrepriseUtilisateur;
                this.plateformeUtilisateur = plateformeUtilisateur;
                this.codeUtilisateur = codeUtilisateur;
                this.typeUtilisateur = typeUtilisateur;

                // Méthode pour obtenir le type en tant que String
//                public String getTypeAsString() {
//                        return type != null ? type.name() : null; // Renvoie le nom de l'enum ou null
//                }
        }

        public String getTypeUtilisateur() {
                return typeUtilisateur;
        }

        public void setTypeUtilisateur(String typeUtilisateur) {
                this.typeUtilisateur = typeUtilisateur;
        }

}