package fr.initiativedeuxsevres.trouve_ton_match.controller;

import fr.initiativedeuxsevres.trouve_ton_match.dto.SecteurReseauDto;
import fr.initiativedeuxsevres.trouve_ton_match.entity.SecteurReseau;
import fr.initiativedeuxsevres.trouve_ton_match.service.SecteurReseauService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/secteurReseau")
public class SecteurReseauController {

    private final SecteurReseauService secteurReseauService;

    @GetMapping(value = "/listeSecteurReseau", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SecteurReseauDto>> getSecteursReseaux() {
        List<SecteurReseauDto> secteurReseau = secteurReseauService.findAll();
        return ResponseEntity.ok(secteurReseau);

//    public ResponseEntity<List<Map<String, Object>>> getSecteursReseaux() {
//        List<Map<String, Object>> secteurs = new ArrayList<>();
//        Map<String, Object> secteur1 = new HashMap<>();
//        secteur1.put("id", 1);
//        secteur1.put("label", "Activités de services administratifs et de soutien");
//        secteurs.add(secteur1);
//        return ResponseEntity.ok(secteurs);
    }

}