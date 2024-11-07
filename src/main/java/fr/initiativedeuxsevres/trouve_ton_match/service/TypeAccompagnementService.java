package fr.initiativedeuxsevres.trouve_ton_match.service;

import fr.initiativedeuxsevres.trouve_ton_match.entity.TypeAccompagnement;
import fr.initiativedeuxsevres.trouve_ton_match.repository.TypeAccompagnementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeAccompagnementService {

    @Autowired
    private TypeAccompagnementRepository typeAccompagnementRepository;

    /**
     * Récupère tous les TypeAccompagnement par une liste d'IDs.
     *
     * @param ids Liste des IDs des TypeAccompagnement à récupérer.
     * @return Liste des TypeAccompagnement correspondants.
     */
    public List<TypeAccompagnement> findAllById(List<Long> ids) {
        // Utiliser le repository pour trouver tous les TypeAccompagnement par IDs
        return typeAccompagnementRepository.findAllById(ids);
    }

    /**
     * Sauvegarde une liste de TypeAccompagnement en fonction d'une liste d'IDs.
     * Si un TypeAccompagnement n'existe pas pour un ID donné, il est créé.
     *
     * @param ids Liste des IDs des TypeAccompagnement à sauvegarder
     * @return Liste des TypeAccompagnement sauvegardés ou mis à jour
     */
    public List<TypeAccompagnement> sauvegarderListeTypeAccompagnement(List<Long> ids) {
        // Récupère les TypeAccompagnement existants par IDs sous forme de Set pour éviter les doublons
        Set<Long> IdsExistants = typeAccompagnementRepository.findAllById(ids).stream()
                .map(TypeAccompagnement::getId)
                .collect(Collectors.toSet());

        // Crée les nouveaux accompagnements pour les IDs manquants
        List<TypeAccompagnement> nouveauxAccompagnements = ids.stream()
                .filter(id -> !IdsExistants.contains(id))
                .map(id -> TypeAccompagnement.builder().id(id).name("Nom par défaut " + id).build())
                .collect(Collectors.toList());

        // Sauvegarde les nouveaux accompagnements uniquement s'il y en a
        List<TypeAccompagnement> accompagementsSauvegardes = Collections.emptyList();
        if (!nouveauxAccompagnements.isEmpty()) {
            accompagementsSauvegardes = typeAccompagnementRepository.saveAll(nouveauxAccompagnements);
        }

        // Combine les éléments existants et les nouveaux accompagnements dans une liste
        // Utilise un Set pour garantir l'unicité des éléments
        Set<TypeAccompagnement> accompagenemtsCombines = new HashSet<>(typeAccompagnementRepository.findAllById(ids));
        accompagenemtsCombines.addAll(accompagementsSauvegardes);

        // Retourne une liste de tous les éléments combinés
        return new ArrayList<>(accompagenemtsCombines);
    }
}
