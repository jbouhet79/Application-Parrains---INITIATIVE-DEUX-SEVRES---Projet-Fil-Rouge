package fr.initiativedeuxsevres.trouve_ton_match.controller;

import fr.initiativedeuxsevres.trouve_ton_match.dto.ParrainDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.PorteurDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.UtilisateurDto;
import fr.initiativedeuxsevres.trouve_ton_match.service.ParrainService;
import fr.initiativedeuxsevres.trouve_ton_match.service.PorteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creationCompte")
public class UtilisateurController {

    private ParrainService parrainService;

    private PorteurService porteurService;

    @Autowired
    public UtilisateurController(ParrainService parrainService, PorteurService porteurService) {
        this.parrainService = parrainService;
        this.porteurService = porteurService;
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

}
