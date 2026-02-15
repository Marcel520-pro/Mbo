package org.logonedigital.mboa_care.controllers;

import jakarta.validation.Valid;
import org.logonedigital.mboa_care.dto.ModificationResDto;
import org.logonedigital.mboa_care.dto.UtilisateurReqDto;
import org.logonedigital.mboa_care.dto.UtilisateurResDto;
import org.logonedigital.mboa_care.service.ProfilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profils")
public class profilControllers {
    private final ProfilService profilService;

    public profilControllers(ProfilService profilService) {
        this.profilService = profilService;
    }

    @PostMapping(path = "/creer_profile")
    public ResponseEntity<String> creerProfil(@RequestBody @Valid UtilisateurReqDto utilisateurReqDto) {
        this.profilService.creerProfil(utilisateurReqDto);
        return ResponseEntity.status(200).body("profil successfully created");
    }

    @GetMapping(path = "/consulter_profil{idUtilisateur}")
    public ResponseEntity<UtilisateurResDto> consulterProfil(@PathVariable("idUtilisateur") String idUtilisateur) {
        return ResponseEntity.status(200).body(this.profilService.consulterProfil(idUtilisateur));
    }

    @GetMapping(path = "/afficher_tous_profiles")
    public ResponseEntity<List<UtilisateurResDto>> getAllProfils() {
        return ResponseEntity.status(200).body(this.profilService.listerProfil());
    }

    @PatchMapping(path = "/modifier_profil{idUtilisateur}")
    public ResponseEntity<String> modifierProfil(@PathVariable("idUtilisateur") String idUtilisateur,
                                                 @RequestBody @Valid ModificationResDto modificationResDto) {
        profilService.modifierProfil(idUtilisateur, modificationResDto);
        return ResponseEntity.status(200).body("profil successfully modified");
    }

    @DeleteMapping(path = "/supprimer_profile{idUtilisateur}")
    public ResponseEntity<String> deleteProfil(@PathVariable("idUtilisateur") String idUtilisateur) {
        this.profilService.supprimerProfil(idUtilisateur);
        return ResponseEntity.status(200).body("profil successfully deleted");
    }

    @GetMapping(path = "/afficher_tous_patients")
    public ResponseEntity<List<UtilisateurResDto>> getAllPatients() {
        return ResponseEntity.status(200).body(this.profilService.getAllPatients());
    }
}
