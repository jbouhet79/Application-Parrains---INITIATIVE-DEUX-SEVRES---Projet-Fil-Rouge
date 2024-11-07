package fr.initiativedeuxsevres.trouve_ton_match.repository;

import fr.initiativedeuxsevres.trouve_ton_match.entity.TypeAccompagnement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeAccompagnementRepository extends JpaRepository<TypeAccompagnement, Long> {
    // JpaRepository fournit déjà la méthode findAllById

}
