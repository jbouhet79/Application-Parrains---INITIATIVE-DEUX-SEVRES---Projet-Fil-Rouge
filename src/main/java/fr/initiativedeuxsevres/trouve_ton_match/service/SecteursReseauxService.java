package fr.initiativedeuxsevres.trouve_ton_match.service;

import fr.initiativedeuxsevres.trouve_ton_match.entity.SecteursReseaux;
import fr.initiativedeuxsevres.trouve_ton_match.repository.SecteursReseauxRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class SecteursReseauxService {

    @Autowired
    private SecteursReseauxRepository secteursReseauxRepository;

    /**
     * Récupère tous les SecteursReseaux par une liste d'IDs.
     *
     * @param ids Liste des IDs des SecteursReseaux à récupérer.
     * @return Liste des SecteursReseaux correspondants.
     */
    public List<SecteursReseaux> findAllById(List<Long> ids) {
        // Utiliser le repository pour trouver tous les SecteursReseaux par IDs
//        return secteursReseauxRepository.findAllById(ids);
        List<SecteursReseaux> result = secteursReseauxRepository.findAllById(ids);
        System.out.println("Secteurs réseaux trouvés : " + result);
        return result;
    }

    public void save(SecteursReseaux secteursReseaux) {
        secteursReseauxRepository.save(secteursReseaux);
    }

    /**
     * Sauvegarde une liste de SecteursReseaux en fonction d'une liste d'IDs.
     * Si un SecteursReseaux n'existe pas pour un ID donné, il est créé.
     *
     * @param ids Liste des IDs des SecteursReseaux à sauvegarder
     * @return Liste des SecteursReseaux sauvegardés ou mis à jour
     */
    public List<SecteursReseaux> sauvegarderListeSecteursReseaux(List<Long> ids) {
        // Récupère les SecteursReseaux existants par IDs sous forme de Set pour éviter
        // les doublons
        Set<Long> IdsExistants = secteursReseauxRepository.findAllById(ids).stream()
                .map(SecteursReseaux::getId)
                .collect(Collectors.toSet());

        // Crée les nouveaux accompagnements pour les IDs manquants
        List<SecteursReseaux> nouveauxAccompagnements = ids.stream()
                .filter(id -> !IdsExistants.contains(id))
                .map(id -> SecteursReseaux.builder()
                        .id(id)
                        .name(Collections.singletonList("Accompagnement " + id)) // Liste de String
                        .build())
                .collect(Collectors.toList());

        // Sauvegarde les nouveaux accompagnements uniquement s'il y en a
        List<SecteursReseaux> accompagementsSauvegardes = Collections.emptyList();
        if (!nouveauxAccompagnements.isEmpty()) {
            accompagementsSauvegardes = secteursReseauxRepository.saveAll(nouveauxAccompagnements);
        }

        // Combine les éléments existants et les nouveaux accompagnements dans une liste
        // Utilise un Set pour garantir l'unicité des éléments
        Set<SecteursReseaux> accompagenemtsCombines = new HashSet<>(secteursReseauxRepository.findAllById(ids));
        accompagenemtsCombines.addAll(accompagementsSauvegardes);

        // Retourne une liste de tous les éléments combinés
        return new ArrayList<>(accompagenemtsCombines);
    }
}
