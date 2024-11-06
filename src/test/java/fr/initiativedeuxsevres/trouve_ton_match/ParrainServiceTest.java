package fr.initiativedeuxsevres.trouve_ton_match;

import fr.initiativedeuxsevres.trouve_ton_match.entity.Parrain;
import fr.initiativedeuxsevres.trouve_ton_match.repository.ParrainRepository;
import fr.initiativedeuxsevres.trouve_ton_match.service.ParrainService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ParrainServiceTest {

    @Mock
    private ParrainRepository parrainRepository;

    @InjectMocks
    private ParrainService parrainService;

    @Test
    public void completerCompteParrain() {

        Long idUtilisateur = 1L;

        String presentationParcours = "Nouvelle Présentation";
        String branchesReseau = "Nouveaux Branches";
        String domainesExpertise = "Nouveaux Domaines";
        String secteurGeographique = "Nouveau Secteur";
        String disponibilites = "Nouvelles Disponibilités";

        Parrain mockParrain = new Parrain(
                "pres",
                "TP",
                "comptabilité",
                "NIORT",
                "5j");

        when(parrainRepository.findById(idUtilisateur)).thenReturn(Optional.of(mockParrain));

        Parrain parrain = parrainService.completerCompteParrain(
                presentationParcours,
                branchesReseau,
                domainesExpertise,
                secteurGeographique,
                disponibilites);

        assertEquals(presentationParcours, parrain.getPresentationParcours());
        assertEquals(branchesReseau, parrain.getBranchesReseau());
        assertEquals(domainesExpertise, parrain.getDomainesExpertise());
        assertEquals(secteurGeographique, parrain.getSecteurGeographique());
        assertEquals(disponibilites, parrain.getDisponibilites());
    }
}
