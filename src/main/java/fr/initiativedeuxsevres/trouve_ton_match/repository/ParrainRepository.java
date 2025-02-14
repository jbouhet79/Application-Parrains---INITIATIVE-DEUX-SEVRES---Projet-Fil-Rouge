package fr.initiativedeuxsevres.trouve_ton_match.repository;

import fr.initiativedeuxsevres.trouve_ton_match.entity.Parrain;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ParrainRepository extends JpaRepository<Parrain, Long> {

    // Méthode pour rechercher un utilisateur par codeUtilisateur.
    Parrain findByCodeUtilisateur(String codeUtilisateur);

    Optional<Parrain> findByNomUtilisateurAndPrenomUtilisateurAndCodeUtilisateur(String nom, String prenom, String code);

//    Parrain findById(Long idUtilisateur);
}


