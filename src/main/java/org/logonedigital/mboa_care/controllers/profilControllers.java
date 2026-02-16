package org.logonedigital.mboa_care.controllers;

import jakarta.validation.Valid;
import org.logonedigital.mboa_care.dto.UtilisateurReqDto;
import org.logonedigital.mboa_care.service.ProfilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/profils")
public class profilControllers {
    private final ProfilService profilService;

    public profilControllers(ProfilService profilService) {
        this.profilService = profilService;
    }

    @PostMapping(path = "ajouter_patient")
    public ResponseEntity<String> creerPatient(@RequestBody @Valid UtilisateurReqDto utilisateurReqDto) {
        this.profilService.ajouterPatient(utilisateurReqDto);
        return ResponseEntity.status(200).body("Patient added successfully");
    }

}
