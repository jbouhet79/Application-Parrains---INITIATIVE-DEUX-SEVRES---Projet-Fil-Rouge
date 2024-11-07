package fr.initiativedeuxsevres.trouve_ton_match.repository;

import fr.initiativedeuxsevres.trouve_ton_match.entity.Porteur;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PorteurRepository extends JpaRepository<Porteur, Long> {
    // Méthode pour rechercher un utilisateur par codeUtilisateur.
    Porteur findByCodeUtilisateur(String codeUtilisateur);

    boolean existsByNomUtilisateurAndPrenomUtilisateurAndCodeUtilisateur(String nom, String prenom, String code);
}
