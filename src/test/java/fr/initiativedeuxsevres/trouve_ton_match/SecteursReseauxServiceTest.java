package fr.initiativedeuxsevres.trouve_ton_match;

import fr.initiativedeuxsevres.trouve_ton_match.entity.SecteursReseaux;
import fr.initiativedeuxsevres.trouve_ton_match.repository.SecteursReseauxRepository;
import fr.initiativedeuxsevres.trouve_ton_match.service.SecteursReseauxService;
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
public class SecteursReseauxServiceTest {

    @Mock
    private SecteursReseauxServiceRepository secteursReseauxServiceRepository;

    @InjectMocks
    private SecteursReseauxService secteursReseauxService;

    @Test
    public void sauvegarderListeSecteursReseaux() {
        // Création de la liste d'IDs à sauvegarder
        List<Long> ids = Collections.singletonList(1L);

        // Création d'un objet SecteursReseaux à sauvegarder
        SecteursReseaux mockSecteursReseaux = SecteursReseaux.builder()
                .id(1L)
                .name("SecteursReseaux Test")
                .build();

        // Simule que l'objet est manquant en base de données
        when(secteursReseauxRepository.findAllById(ids)).thenReturn(Collections.emptyList());

        // Simule le comportement du repository pour sauvegarder l'objet manquant
        when(secteursReseauxRepository.saveAll(anyList())).thenReturn(Collections.singletonList(mockSecteursReseaux));

        // Appel de la méthode de sauvegarde
        List<SecteursReseaux> result = secteursReseauxService.sauvegarderListeSecteursReseaux(ids);

        // Vérification que l'objet retourné est bien celui sauvegardé
        assertNotNull(result, "La liste des objets sauvegardés ne doit pas être null");
        assertEquals(1, result.size(), "La liste doit contenir un seul objet sauvegardé");
        assertEquals(mockSecteursReseaux.getId(), result.get(0).getId(), "L'ID de l'objet sauvegardé doit correspondre");
        assertEquals(mockSecteursReseaux.getName(), result.get(0).getName(), "Le nom de l'objet sauvegardé doit correspondre");

        // Vérifie que la méthode saveAll du repository a été appelée une fois
        verify(secteursReseauxRepository).saveAll(anyList());
    }
}
