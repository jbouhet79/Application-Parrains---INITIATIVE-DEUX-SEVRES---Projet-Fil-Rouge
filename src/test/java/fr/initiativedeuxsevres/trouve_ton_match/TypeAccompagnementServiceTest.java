package fr.initiativedeuxsevres.trouve_ton_match;

import fr.initiativedeuxsevres.trouve_ton_match.entity.TypeAccompagnement;
import fr.initiativedeuxsevres.trouve_ton_match.repository.TypeAccompagnementRepository;
import fr.initiativedeuxsevres.trouve_ton_match.service.TypeAccompagnementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TypeAccompagnementServiceTest {

    @Mock
    private TypeAccompagnementRepository typeAccompagnementRepository;

    @InjectMocks
    private TypeAccompagnementService typeAccompagnementService;

    @Test
    public void sauvegarderListeTypeAccompagnement() {
        // Création de la liste d'IDs à sauvegarder
        List<Long> ids = Collections.singletonList(1L);

        // Création d'un objet TypeAccompagnement à sauvegarder
        TypeAccompagnement mockAccompagnement = TypeAccompagnement.builder()
                .id(1L)
                .name("Accompagnement Test")
                .build();

        // Simule que l'objet est manquant en base de données
        when(typeAccompagnementRepository.findAllById(ids)).thenReturn(Collections.emptyList());

        // Simule le comportement du repository pour sauvegarder l'objet manquant
        when(typeAccompagnementRepository.saveAll(anyList())).thenReturn(Collections.singletonList(mockAccompagnement));

        // Appel de la méthode de sauvegarde
        List<TypeAccompagnement> result = typeAccompagnementService.sauvegarderListeTypeAccompagnement(ids);

        // Vérification que l'objet retourné est bien celui sauvegardé
        assertNotNull(result, "La liste des objets sauvegardés ne doit pas être null");
        assertEquals(1, result.size(), "La liste doit contenir un seul objet sauvegardé");
        assertEquals(mockAccompagnement.getId(), result.get(0).getId(), "L'ID de l'objet sauvegardé doit correspondre");
        assertEquals(mockAccompagnement.getName(), result.get(0).getName(), "Le nom de l'objet sauvegardé doit correspondre");

        // Vérifie que la méthode saveAll du repository a été appelée une fois
        verify(typeAccompagnementRepository).saveAll(anyList());
    }

//    @Test
//    public void sauvegarderListeTypeAccompagnement() {
//        // Création de la liste d'IDs à sauvegarder
//        List<Long> ids = Arrays.asList(1L);
//
//        // Création d'un objet TypeAccompagnement à sauvegarder
//        TypeAccompagnement mockAccompagnement = TypeAccompagnement.builder()
//                .id(1L)
//                .name("Accompagnement Test")
//                .build();
//
//        // Simule que l'objet est manquant en base de données
//        when(typeAccompagnementRepository.findAllById(ids)).thenReturn(Collections.emptyList());
//
//        // Simule le comportement du repository pour sauvegarder l'objet manquant
//        when(typeAccompagnementRepository.saveAll(anyList())).thenReturn(Collections.singletonList(mockAccompagnement));
//
//        // Appel de la méthode de sauvegarde
//        List<TypeAccompagnement> result = typeAccompagnementService.sauvegarderListeTypeAccompagnement(ids);
//
//        // Vérification que l'objet retourné est bien celui sauvegardé
//        assertNotNull(result, "La liste des objets sauvegardés ne doit pas être null");
//        assertEquals(1, result.size(), "La liste doit contenir un seul objet sauvegardé");
//        assertEquals(mockAccompagnement.getId(), result.get(0).getId(), "L'ID de l'objet sauvegardé doit correspondre");
//        assertEquals(mockAccompagnement.getName(), result.get(0).getName(), "Le nom de l'objet sauvegardé doit correspondre");
//
//        // Vérifie que la méthode saveAll du repository a été appelée une fois
//        verify(typeAccompagnementRepository, times(1)).saveAll(anyList());
//    }
}
