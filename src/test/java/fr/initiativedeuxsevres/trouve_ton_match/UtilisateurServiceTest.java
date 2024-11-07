package fr.initiativedeuxsevres.trouve_ton_match;

import fr.initiativedeuxsevres.trouve_ton_match.service.UtilisateurService;
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
public class UtilisateurServiceTest {

    @Mock
    private TypeAccompagnementRepository typeAccompagnementRepository;

    @InjectMocks
    private TypeAccompagnementService typeAccompagnementService;

    @Test
    public void sauvegarderTypeAccompagnement() {

        Long idUtilisateur = 1L;

        boolean ressourcesHumaines = true;
        boolean financesComptabilité = true;
        boolean juridique = true;
        boolean informatique = true;
        boolean commercialCommunication = true;

        TypeAccompagnement mockTypeAccompagnement = new TypeAccompagnement (
                ressourcesHumaines,
                financesComptabilité,
                juridique,
                informatique,
                commercialCommunication);

        when(typeAccompagnementRepository.findById(idUtilisateur)).thenReturn(Optional.of(mockTypeAccompagnement));
        when(typeAccompagnementRepository.save(any(TypeAccompagnement.class))).thenReturn(mockTypeAccompagnement);

        TypeAccompagnement typeAccompagnementTest = typeAccompagnementService.sauvegarderTypeAccompagnement(
                idUtilisateur,
                ressourcesHumaines,
                financesComptabilité,
                juridique,
                informatique,
                commercialCommunication);

        assertEquals(ressourcesHumaines, typeAccompagnementTest.getRessoucesHumaines());
        assertEquals(financesComptabilité, typeAccompagnementTest.getFinancesComptabilité());
        assertEquals(juridique, typeAccompagnementTest.getJuridique());
        assertEquals(informatique, typeAccompagnementTest.getInformatique());
        assertEquals(commercialCommunication, typeAccompagnementTest.getCommercialCommunication());
    }
}
