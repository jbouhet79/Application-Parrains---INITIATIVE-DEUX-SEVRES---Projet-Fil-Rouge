package fr.initiativedeuxsevres.trouve_ton_match.controller;

import fr.initiativedeuxsevres.trouve_ton_match.dto.ParrainDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.PorteurDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.UtilisateurDto;
import fr.initiativedeuxsevres.trouve_ton_match.service.ParrainService;
import fr.initiativedeuxsevres.trouve_ton_match.service.PorteurService;
import fr.initiativedeuxsevres.trouve_ton_match.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/creationCompte")
public class UtilisateurController {

    private ParrainService parrainService;

    private PorteurService porteurService;

    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(ParrainService parrainService, PorteurService porteurService, UtilisateurService utilisateurService) {
        this.parrainService = parrainService;
        this.porteurService = porteurService;
        this.utilisateurService = utilisateurService;
    }

    @PostMapping(value = "/createutilisateur", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UtilisateurDto> createUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        UtilisateurDto utilisateur;

        if (utilisateurDto.getType().equals("parrain"))
        {
             utilisateur = parrainService.createParrain( new ParrainDto(utilisateurDto));
        }
        else
        {
             utilisateur = porteurService.createPorteurOld(new PorteurDto(utilisateurDto));
        }

        return ResponseEntity.ok(utilisateur);
    }

    // La méthode getConnexionUtilisateur utilise @RequestParam pour obtenir le codeUtilisateur de la requête.
    // Elle appelle ensuite le service pour récupérer les informations de l’utilisateur et retourne les informations avec un statut HTTP approprié.
    @GetMapping(value = "/connexionutilisateur", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UtilisateurDto> getConnexionUtilisateur(@RequestParam String codeUtilisateur) {
        UtilisateurDto utilisateur = utilisateurService.findByCodeUtilisateur(codeUtilisateur);
        
        if (utilisateur != null) {
            return ResponseEntity.ok(utilisateur);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    // La méthode répond aux requêtes HTTP POST envoyées à l’URL /checkutilisateur. La réponse sera au format JSON
    @PostMapping(value = "/checkutilisateur", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Boolean>> checkUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        boolean exists = utilisateurService.existsByNomPrenomCode(
                utilisateurDto.getNomUtilisateur(),
                utilisateurDto.getPrenomUtilisateur(),
                utilisateurDto.getCodeUtilisateur()
        );
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }
}
