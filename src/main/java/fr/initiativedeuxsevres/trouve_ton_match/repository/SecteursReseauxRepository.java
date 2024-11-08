package fr.initiativedeuxsevres.trouve_ton_match.repository;

import fr.initiativedeuxsevres.trouve_ton_match.entity.SecteursReseaux;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecteursReseauxRepository extends JpaRepository<SecteursReseaux, Long> {
    // JpaRepository fournit déjà la méthode findAllById

}
