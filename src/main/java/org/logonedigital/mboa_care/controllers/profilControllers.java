package org.logonedigital.mboa_care.controllers;

import jakarta.validation.Valid;
import org.logonedigital.mboa_care.dto.UtilisateurReqDto;
import org.logonedigital.mboa_care.service.ProfilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
