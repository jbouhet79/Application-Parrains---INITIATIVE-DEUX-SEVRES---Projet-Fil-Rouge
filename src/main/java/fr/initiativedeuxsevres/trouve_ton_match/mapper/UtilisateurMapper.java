package fr.initiativedeuxsevres.trouve_ton_match.mapper;

import fr.initiativedeuxsevres.trouve_ton_match.dto.*;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Parrain;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Porteur;
import fr.initiativedeuxsevres.trouve_ton_match.entity.TypeAccompagnement;
import fr.initiativedeuxsevres.trouve_ton_match.entity.Utilisateur;

import java.util.List;


public class UtilisateurMapper {


    public UtilisateurMapper() {
        // Initialisation si nécessaire
    }


    public static UtilisateurDto toUtilisateurDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        UtilisateurDto utilisateurDto;

        if (utilisateur instanceof Parrain) {
            utilisateurDto = new ParrainMapper().toDto(utilisateur);
        } else if (utilisateur instanceof Porteur) {
            utilisateurDto = new PorteurMapper().toDto(utilisateur);
        } else {
            throw new IllegalArgumentException();
        }

        return utilisateurDto;
    }


    public static Utilisateur toUtilisateurEntity(UtilisateurDto utilisateurDto) {
        if (utilisateurDto == null) {
            return null;
        }

        Utilisateur utilisateurEntity;

        if (utilisateurDto instanceof ParrainDto || utilisateurDto.getTypeUtilisateur().equals("parrain")) {
            utilisateurEntity = new ParrainMapper().toEntity(utilisateurDto);
        } else if (utilisateurDto instanceof PorteurDto || utilisateurDto.getTypeUtilisateur().equals("porteur")) {
            utilisateurEntity = new PorteurMapper().toEntity(utilisateurDto);
        } else {
            throw new IllegalArgumentException();
        }

        return utilisateurEntity;
    }


    /**
     * Convertit une entité Utilisateur en UtilisateurDto.
     *
     * @param utilisateur L'entité Utilisateur à convertir.
     * @return Un UtilisateurDto correspondant.
     */
    protected UtilisateurDto toDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        UtilisateurDto utilisateurDto;

        // utilisation de instanceof afin de s'assurer du bon type d'utilisateurDto
        if (utilisateur instanceof Parrain) {
            utilisateurDto = new ParrainDto();
        } else if (utilisateur instanceof Porteur) {
            utilisateurDto = new PorteurDto();
        } else {
            throw new IllegalArgumentException();
        }

        utilisateurDto.setIdUtilisateur(utilisateur.getIdUtilisateur());
        utilisateurDto.setNomUtilisateur(utilisateur.getNomUtilisateur());
        utilisateurDto.setPrenomUtilisateur(utilisateur.getPrenomUtilisateur());
        utilisateurDto.setEntrepriseUtilisateur(utilisateur.getEntrepriseUtilisateur());
        utilisateurDto.setPlateformeUtilisateur(utilisateur.getPlateformeUtilisateur());
        utilisateurDto.setCodeUtilisateur(utilisateur.getCodeUtilisateur());
        utilisateurDto.setTypeUtilisateur(utilisateur.getTypeUtilisateur());


        // Associer les entités accompagnementTypeList
        List<TypeAccompagnement> listAccom = utilisateur.getAccompagnementTypeList();
        if (listAccom != null && !listAccom.isEmpty()) {
            utilisateurDto.setAccompagnementTypeList(new TypeAccompagnementMapper().toDtoList(listAccom));
        }


        List<SecteurReseauDto> listSectDto = utilisateurDto.getSecteurReseauList();
        if (listSectDto != null && !listSectDto.isEmpty()) {
            utilisateur.setSecteurReseauList(new SecteurReseauMapper().toEntityList(listSectDto));
        }

        return utilisateurDto;
    }

    /**
     * Convertit un UtilisateurDto en entité Utilisateur.
     *
     * @param utilisateurDto Le DTO à convertir.
     * @return Une entité Utilisateur correspondant.
     */

    protected Utilisateur toEntity(UtilisateurDto utilisateurDto) {
        System.out.println("utilisateurMapper toEntity ================================================ ");
        if (utilisateurDto == null) {
            return null;
        }

        Utilisateur utilisateur;

        // utilisation de instanceof afin de s'assurer du bon type d'utilisateurDto
        if (utilisateurDto instanceof ParrainDto || utilisateurDto.getTypeUtilisateur().equals("parrain")) {
            utilisateur = new Parrain();

        } else if (utilisateurDto instanceof PorteurDto || utilisateurDto.getTypeUtilisateur().equals("porteur")) {
            utilisateur = new Porteur();
        } else {
            throw new IllegalArgumentException();
        }

        // Mapping des champs
        utilisateur.setIdUtilisateur(utilisateurDto.getIdUtilisateur());
        utilisateur.setNomUtilisateur(utilisateurDto.getNomUtilisateur());
        utilisateur.setPrenomUtilisateur(utilisateurDto.getPrenomUtilisateur());
        utilisateur.setEntrepriseUtilisateur(utilisateurDto.getEntrepriseUtilisateur());
        utilisateur.setPlateformeUtilisateur(utilisateurDto.getPlateformeUtilisateur());
        utilisateur.setCodeUtilisateur(utilisateurDto.getCodeUtilisateur());
        utilisateur.setTypeUtilisateur(utilisateurDto.getTypeUtilisateur());


        // Associer les entités accompagnementTypeList
        List<TypeAccompagnementDto> listAccomDto = utilisateurDto.getAccompagnementTypeList();

        if (listAccomDto != null && !listAccomDto.isEmpty()) {
            utilisateur.setAccompagnementTypeList(new TypeAccompagnementMapper().toEntityList(listAccomDto));
        }


        List<SecteurReseauDto> listSectDto = utilisateurDto.getSecteurReseauList();
        if (listSectDto != null && !listSectDto.isEmpty()) {
            utilisateur.setSecteurReseauList(new SecteurReseauMapper().toEntityList(listSectDto));
        }

        return utilisateur;
    }
}