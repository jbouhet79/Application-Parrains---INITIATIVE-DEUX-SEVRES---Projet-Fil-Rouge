package fr.initiativedeuxsevres.trouve_ton_match.controller;

import fr.initiativedeuxsevres.trouve_ton_match.dto.ParrainDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.PorteurDto;
import fr.initiativedeuxsevres.trouve_ton_match.dto.UtilisateurDto;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Parrain;
import fr.initiativedeuxsevres.trouve_ton_match.entity.TypeAccompagnement;
import fr.initiativedeuxsevres.trouve_ton_match.entity.SecteursReseaux;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Utilisateur;
import fr.initiativedeuxsevres.trouve_ton_match.service.ParrainService;
import fr.initiativedeuxsevres.trouve_ton_match.service.PorteurService;
import fr.initiativedeuxsevres.trouve_ton_match.service.TypeAccompagnementService;
import fr.initiativedeuxsevres.trouve_ton_match.service.SecteursReseauxService;
import fr.initiativedeuxsevres.trouve_ton_match.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/creationCompte")
public class UtilisateurController {

    @Autowired
    private ParrainService parrainService;

    @Autowired
    private PorteurService porteurService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private TypeAccompagnementService typeAccompagnementService;

    @Autowired
    private SecteursReseauxService secteursReseauxService;


    @Autowired
    public UtilisateurController(ParrainService parrainService, PorteurService porteurService, UtilisateurService utilisateurService, TypeAccompagnementService typeAccompagnementService) {
        this.parrainService = parrainService;
        this.porteurService = porteurService;
        this.utilisateurService = utilisateurService;
        this.typeAccompagnementService = typeAccompagnementService;
        this.secteursReseauxService = secteursReseauxService;
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
    public ResponseEntity<Utilisateur> checkUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        Optional<Utilisateur> utilisateur = utilisateurService.getByNomPrenomCode(
                utilisateurDto.getNomUtilisateur(),
                utilisateurDto.getPrenomUtilisateur(),
                utilisateurDto.getCodeUtilisateur()
        );

        // Vérifie si un utilisateur est trouvé et renvoie une réponse adéquate
        return utilisateur
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping(value = "/completercompteparrain", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Parrain> completercompteparrain(@RequestBody ParrainDto parrainDto) {

        System.out.println("Données reçues : " + parrainDto);

        Parrain parrain = parrainService.findById(parrainDto.getIdUtilisateur());

        // Vérifier si le parrain existe dans la base
        if (parrain == null) {
            // Si le parrain n'existe pas, retourner un objet Parrain vide
            Parrain emptyParrain = new Parrain();
            emptyParrain.setIdUtilisateur(parrainDto.getIdUtilisateur());
            System.out.println("Parrain introuvable, renvoi d'un objet vide.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(emptyParrain);
        }

        System.out.println("Compte complété");
        System.out.println("id :" + parrainDto.getIdUtilisateur());

        // Mise à jour des données
        parrain.setPresentationParcours(parrainDto.getPresentationParcours());
        parrain.setBranchesReseau(parrainDto.getBranchesReseau());
        parrain.setDomainesExpertise(parrainDto.getDomainesExpertise());
        parrain.setSecteurGeographique(parrainDto.getSecteurGeographique());
        parrain.setDisponibilites(parrainDto.getDisponibilites());

        Parrain updatedParrain = parrainService.save(parrain);
        return ResponseEntity.ok(updatedParrain);

//        try {
//            Parrain updatedParrain = parrainService.save(parrain);
//            return ResponseEntity.ok(updatedParrain);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Erreur lors de la sauvegarde : " + e.getMessage());
//        }
    }

    @PostMapping(value = "/accompagnementutilisateur", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> accompagnementUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        List<TypeAccompagnement> accompagnementTypeList = typeAccompagnementService.findAllById(utilisateurDto.getAccompagnementTypeList());

        Utilisateur utilisateur = utilisateurService.findByCodeUtilisateur(utilisateurDto.getCodeUtilisateur());
        utilisateur.setAccompagnementTypeList(accompagnementTypeList);

        Utilisateur newUser = utilisateurService.save(utilisateur);

        return ResponseEntity.ok(newUser);
    }

    @PostMapping(value = "/secteursreseauxutilisateur", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> secteursReseauxUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        List<SecteursReseaux> secteursReseauxList = secteursReseauxService.findAllById(utilisateurDto.getSecteursReseauxList());

        Utilisateur utilisateur = utilisateurService.findByCodeUtilisateur(utilisateurDto.getCodeUtilisateur());
        utilisateur.setSecteursReseauxList(secteursReseauxList);

        Utilisateur newUser = utilisateurService.save(utilisateur);

        return ResponseEntity.ok(newUser);
    }

    @PostMapping(value = "/filtres", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> selectionFiltres(@RequestBody UtilisateurDto utilisateurDto) {
        System.out.println("Données reçues : " + utilisateurDto);

        // 1. Récupérer l'utilisateur par son ID
        Utilisateur utilisateur = utilisateurService.findById(utilisateurDto.getIdUtilisateur());
        if (utilisateur == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retourne 404 si l'utilisateur n'existe pas
        }

        // 2. Récupérer les IDs des types d'accompagnement et des secteurs
        List<Long> accompagnementIds = utilisateurDto.getAccompagnementTypeList();
        List<Long> secteurIds = utilisateurDto.getSecteursReseauxList();

        // 3. Vérification des listes d'IDs
        if (accompagnementIds == null || secteurIds == null) {
            return ResponseEntity.badRequest().build();
        }

        // 4. Récupérer les types d'accompagnement et secteurs par leurs IDs
        List<TypeAccompagnement> accompagnements = typeAccompagnementService.findAllById(accompagnementIds);
        List<SecteursReseaux> secteurs = secteursReseauxService.findAllById(secteurIds);

        // 5. Gérer les cas où certaines entités n'ont pas été trouvées
        if (accompagnements.size() != accompagnementIds.size()) {
            // Créer les types d'accompagnement manquants
            for (Long id : accompagnementIds) {
                Optional<TypeAccompagnement> existingAccompagnement = accompagnements.stream()
                        .filter(accomp -> accomp.getId().equals(id))
                        .findFirst();

                if (existingAccompagnement.isEmpty()) {
                    // Si l'accompagnement avec cet ID n'est pas trouvé, créer un nouveau type d'accompagnement
                    TypeAccompagnement newAccompagnement = new TypeAccompagnement();
                    newAccompagnement.setName(Collections.singletonList("Accompagnement " + id));  // Définir un nom par exemple
                    // L'ID sera généré automatiquement lors de l'insertion, ne pas définir manuellement
                    typeAccompagnementService.save(newAccompagnement);

                    // Ajouter le nouvel accompagnement à la liste
                    accompagnements.add(newAccompagnement);
                }
            }
        }

        // 6. Idem pour les secteurs
        if (secteurs.size() != secteurIds.size()) {
            for (Long id : secteurIds) {
                if (secteurs.stream().noneMatch(secteur -> secteur.getId().equals(id))) {
                    SecteursReseaux newSecteur = new SecteursReseaux();
                    newSecteur.setId(id);
                    secteursReseauxService.save(newSecteur);
                    secteurs.add(newSecteur);
                }
            }
        }

        // 7. Associer les entités à l'utilisateur
        utilisateur.setAccompagnementTypeList(accompagnements);
        utilisateur.setSecteursReseauxList(secteurs);

        // 8. Sauvegarder les modifications de l'utilisateur
        Utilisateur savedUtilisateur = utilisateurService.save(utilisateur);

        return ResponseEntity.ok(savedUtilisateur);
    }

//    @PostMapping(value = "/filtres", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Utilisateur> selectionFiltres(@RequestBody UtilisateurDto utilisateurDto) {
        // Charger l'utilisateur
//        Utilisateur utilisateur = utilisateurService.findByCodeUtilisateur(utilisateurDto.getCodeUtilisateur());
//
//        // Traiter les types d'accompagnement si présents
//        if (utilisateurDto.getAccompagnementTypeList() != null && !utilisateurDto.getAccompagnementTypeList().isEmpty()) {
//            List<TypeAccompagnement> accompagnementTypeList = typeAccompagnementService.findAllById(utilisateurDto.getAccompagnementTypeList());
//            utilisateur.setAccompagnementTypeList(accompagnementTypeList);
//        }
//
//        // Traiter les secteurs/réseaux si présents
//        if (utilisateurDto.getSecteursReseauxList() != null && !utilisateurDto.getSecteursReseauxList().isEmpty()) {
//            List<SecteursReseaux> secteursReseauxList = secteursReseauxService.findAllById(utilisateurDto.getSecteursReseauxList());
//            utilisateur.setSecteursReseauxList(secteursReseauxList);
//        }
//
//        // Sauvegarder les modifications
//        Utilisateur newUser = utilisateurService.save(utilisateur);
//
//        return ResponseEntity.ok(newUser);

//        try {
//            System.out.println("Données reçues : " + utilisateurDto);
//
//            List<Long> accompagnementIds = utilisateurDto.getAccompagnementTypeList();
//            List<Long> secteurIds = utilisateurDto.getSecteursReseauxList();
//
//            // Vérifiez si les données sont non nulles
//            if (accompagnementIds == null || secteurIds == null) {
//                throw new IllegalArgumentException("Les listes ne doivent pas être nulles");
//            }

//            System.out.println("IDs des types d'accompagnement : " + accompagnementIds);
//            System.out.println("IDs des secteurs/réseaux : " + secteurIds);
//
//            // Simulez ou appelez des services pour traiter les IDs
//            // Par exemple, récupérer les entités correspondantes par leurs IDs :
//            List<TypeAccompagnement> accompagnements = typeAccompagnementService.findAllById(accompagnementIds);
//            List<SecteursReseaux> secteurs = secteursReseauxService.findAllById(secteurIds);
//
//            System.out.println("Accompagnements trouvés : " + accompagnements);
//            System.out.println("Secteurs trouvés : " + secteurs);

            // Récupération ou création des types d'accompagnement
//            List<TypeAccompagnement> accompagnements = typeAccompagnementService.findAllById(accompagnementIds);
//            for (Long id : accompagnementIds) {
//                if (accompagnements.stream().noneMatch(accomp -> accomp.getId().equals(id))) {
//                    // Si l'accompagnement n'existe pas, on le crée
//                    TypeAccompagnement newAccompagnement = new TypeAccompagnement();
//                    newAccompagnement.setId(id); // ou d'autres données nécessaires
//
//                    // Créer une liste de String avec le nom de l'accompagnement
//                    newAccompagnement.setName("Nom de l'accompagnement " + id);
//
//                    // Ajouter le nouvel accompagnement à la liste
//                    accompagnements.add(newAccompagnement);
//
//                    // Sauvegarder le nouvel accompagnement
//                    typeAccompagnementService.save(newAccompagnement);
//                }
//            }
//
//            // Récupération ou création des secteurs/réseaux
//            List<SecteursReseaux> secteurs = secteursReseauxService.findAllById(secteurIds);
//            for (Long id : secteurIds) {
//                if (secteurs.stream().noneMatch(secteur -> secteur.getId().equals(id))) {
//                    // Si le secteur n'existe pas, on le crée
//                    SecteursReseaux newSecteur = new SecteursReseaux();
//                    newSecteur.setId(id); // ou d'autres données nécessaires
//                    newSecteur.setName(Collections.singletonList("Secteur " + id));
//                    secteurs.add(newSecteur);
//                    secteursReseauxService.save(newSecteur);
//                }
//            }
//
//
//            // Associez-les à l'utilisateur
//            // Utilisateur utilisateur = utilisateurService.findById(utilisateurDto.getIdUtilisateur());
//            Utilisateur utilisateur = utilisateurService.findById(utilisateurDto.getIdUtilisateur());
//            if (utilisateur == null) {
//                System.out.println("Utilisateur avec ID " + utilisateurDto.getIdUtilisateur() + " non trouvé !");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//            }
//
//            utilisateur.setAccompagnementTypeList(accompagnements);
//            utilisateur.setSecteursReseauxList(secteurs);
//
//            Utilisateur savedUtilisateur = utilisateurService.save(utilisateur);
//            System.out.println("Utilisateur sauvegardé : " + savedUtilisateur);
//
//            return ResponseEntity.ok(savedUtilisateur);
//        } catch (Exception e) {
//            e.printStackTrace(); // Afficher l'exception dans la console
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.findById(id);
        return ResponseEntity.ok(utilisateur);
    }



}
