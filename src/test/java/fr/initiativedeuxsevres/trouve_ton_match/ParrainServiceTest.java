package fr.initiativedeuxsevres.trouve_ton_match;

import fr.initiativedeuxsevres.trouve_ton_match.entity.Parrain;
import fr.initiativedeuxsevres.trouve_ton_match.repository.ParrainRepository;
import fr.initiativedeuxsevres.trouve_ton_match.service.ParrainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParrainServiceTest {

    @Mock
    private ParrainRepository parrainRepository;

    @InjectMocks
    private ParrainService parrainService;

    @Test
    public void completerCompteParrain() {

        // mock
        Long idUtilisateur = 1L;

        String presentationParcours = "pres";
        String branchesReseau = "TP";
        String domainesExpertise = "comptabilité";
        String secteurGeographique = "NIORT";
        String disponibilites = "5j";

        Parrain mockParrain = new Parrain(
                presentationParcours,
                branchesReseau,
                domainesExpertise,
                secteurGeographique,
                disponibilites);

        // when des deux appels à la base de données faits dans la fonction
        when(parrainRepository.findById(idUtilisateur)).thenReturn(Optional.of(mockParrain));
        when(parrainRepository.save(any(Parrain.class))).thenReturn(mockParrain);

        // then
        Parrain parrainTest = parrainService.completerCompteParrain(
                idUtilisateur,
                presentationParcours,
                branchesReseau,
                domainesExpertise,
                secteurGeographique,
                disponibilites);

        assertEquals(presentationParcours, parrainTest.getPresentationParcours());
        assertEquals(branchesReseau, parrainTest.getBranchesReseau());
        assertEquals(domainesExpertise, parrainTest.getDomainesExpertise());
        assertEquals(secteurGeographique, parrainTest.getSecteurGeographique());
        assertEquals(disponibilites, parrainTest.getDisponibilites());
    }
}
