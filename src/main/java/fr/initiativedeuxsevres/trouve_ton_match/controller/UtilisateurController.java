package fr.initiativedeuxsevres.trouve_ton_match.controller;

import fr.initiativedeuxsevres.trouve_ton_match.dto.ParrainDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.PorteurDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.UtilisateurDto;
import fr.initiativedeuxsevres.trouve_ton_match.entity.TypeAccompagnement;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Utilisateur;
import fr.initiativedeuxsevres.trouve_ton_match.service.ParrainService;
import fr.initiativedeuxsevres.trouve_ton_match.service.PorteurService;
import fr.initiativedeuxsevres.trouve_ton_match.service.TypeAccompagnementService;
import fr.initiativedeuxsevres.trouve_ton_match.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/creationCompte")
public class UtilisateurController {

    private ParrainService parrainService;

    private PorteurService porteurService;

    private UtilisateurService utilisateurService;

    private TypeAccompagnementService typeAccompagnementService;
//    private TypeAccompagnementRepository typeAccompagnementService;

    @Autowired
    public UtilisateurController(ParrainService parrainService, PorteurService porteurService, UtilisateurService utilisateurService, TypeAccompagnementService typeAccompagnementService) {
        this.parrainService = parrainService;
        this.porteurService = porteurService;
        this.utilisateurService = utilisateurService;
        this.typeAccompagnementService = typeAccompagnementService;
    }

    @PostMapping(value = "/createutilisateur", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = utilisateurService.save(utilisateurDto);

        return ResponseEntity.ok(utilisateur);
    }

    // La méthode getConnexionUtilisateur utilise @RequestParam pour obtenir le codeUtilisateur de la requête.
    // Elle appelle ensuite le service pour récupérer les informations de l’utilisateur et retourne les informations avec un statut HTTP approprié.
    @GetMapping(value = "/connexionutilisateur", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> getConnexionUtilisateur(@RequestParam String codeUtilisateur) {
        Utilisateur utilisateur = utilisateurService.findByCodeUtilisateur(codeUtilisateur);
        
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

    @PostMapping(value = "/accompagnemantutilisateur", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> accompagnementUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        List<TypeAccompagnement> accompagnementTypeList = typeAccompagnementService.findAllById(utilisateurDto.getAccompagnementTypeList());

        Utilisateur utilisateur = utilisateurService.findByCodeUtilisateur(utilisateurDto.getCodeUtilisateur());
        utilisateur.setAccompagnementTypeList(accompagnementTypeList);

        Utilisateur newUser = utilisateurService.save(utilisateur);

        return ResponseEntity.ok(newUser);
    }

}
