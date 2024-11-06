package fr.initiativedeuxsevres.trouve_ton_match;

import fr.initiativedeuxsevres.trouve_ton_match.service.UtilisateurService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurService utilisateurService;

    @Test
    public void sauvegarderTypeAccompagnement() {

        Long idUtilisateur = 1L;

        boolean ressourcesHumaines = true;
        boolean financesComptabilité = true;
        boolean juridique = true;
        boolean informatique = true;
        boolean commercialCommunication = true;





    }
}
