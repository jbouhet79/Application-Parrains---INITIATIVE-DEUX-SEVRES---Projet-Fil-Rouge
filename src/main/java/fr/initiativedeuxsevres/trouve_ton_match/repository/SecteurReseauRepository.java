package fr.initiativedeuxsevres.trouve_ton_match.repository;

import fr.initiativedeuxsevres.trouve_ton_match.entity.SecteurReseau;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecteurReseauRepository extends JpaRepository<SecteurReseau, Long> {
    // JpaRepository fournit déjà la méthode findAllById

}
