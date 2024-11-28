package fr.initiativedeuxsevres.trouve_ton_match;

import fr.initiativedeuxsevres.trouve_ton_match.entity.SecteurReseau;
import fr.initiativedeuxsevres.trouve_ton_match.repository.SecteurReseauRepository;
import fr.initiativedeuxsevres.trouve_ton_match.service.SecteurReseauService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SecteurReseauServiceTest {

    @Mock
    private SecteurReseauRepository secteurReseauRepository;

    @InjectMocks
    private SecteurReseauService secteurReseauService;

    @Test
    public void sauvegarderListeSecteurReseau() {
        // Création de la liste d'IDs à sauvegarder
        List<Long> ids = Collections.singletonList(1L);

        // Création d'un objet SecteurReseau à sauvegarder
        SecteurReseau mockSecteurReseau = SecteurReseau.builder()
                .id(1L)
                .label("SecteurReseau Test")
                .build();

        // Simule que l'objet est manquant en base de données
        when(secteurReseauRepository.findAllById(ids)).thenReturn(Collections.emptyList());

        // Simule le comportement du repository pour sauvegarder l'objet manquant
        when(secteurReseauRepository.saveAll(anyList())).thenReturn(Collections.singletonList(mockSecteurReseau));

        // Appel de la méthode de sauvegarde
        List<SecteurReseau> result = secteurReseauService.sauvegarderListeSecteurReseau(ids);

        // Vérification que l'objet retourné est bien celui sauvegardé
        assertNotNull(result, "La liste des objets sauvegardés ne doit pas être null");
        assertEquals(1, result.size(), "La liste doit contenir un seul objet sauvegardé");
        assertEquals(mockSecteurReseau.getId(), result.get(0).getId(), "L'ID de l'objet sauvegardé doit correspondre");
        assertEquals(mockSecteurReseau.getLabel(), result.get(0).getLabel(), "Le nom de l'objet sauvegardé doit correspondre");

        // Vérifie que la méthode saveAll du repository a été appelée une fois
        verify(secteurReseauRepository).saveAll(anyList());
    }
}
