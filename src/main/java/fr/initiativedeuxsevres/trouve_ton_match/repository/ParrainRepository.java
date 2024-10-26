package fr.initiativedeuxsevres.trouve_ton_match.repository;

import fr.initiativedeuxsevres.trouve_ton_match.entity.Parrain;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

//public interface ParrainRepository extends JpaRepository <Utilisateur, Long> {
//
//}

public interface ParrainRepository extends JpaRepository<Parrain, Long> {

    // Méthode pour rechercher un utilisateur par codeUtilisateur.
    Parrain findByCodeUtilisateur(String codeUtilisateur);

    boolean existsByNomUtilisateurAndPrenomUtilisateurAndCodeUtilisateur(String nom, String prenom, String code);
}


