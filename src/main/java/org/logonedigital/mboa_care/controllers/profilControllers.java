package org.logonedigital.mboa_care.controllers;

import jakarta.validation.Valid;
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
}
