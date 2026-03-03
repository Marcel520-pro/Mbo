package org.logonedigital.mboa_care.controllers;

import org.logonedigital.mboa_care.dto.MedecinReqDto;
import org.logonedigital.mboa_care.dto.MedecinResDto;
import org.logonedigital.mboa_care.service.MedecinService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medecins")
public class MedecinController {
    private final MedecinService medecinService;

    public MedecinController(MedecinService medecinService) {
        this.medecinService = medecinService;
    }

    @PostMapping
    public ResponseEntity<String> ajouterMedecin(@RequestBody MedecinReqDto medecinReqDto) {
        medecinService.ajouterMedecin(medecinReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Medecin created successfully");
    }

    @DeleteMapping("/{idUtilisateur}")
    public ResponseEntity<String> supprimerMedecin(@PathVariable String idUtilisateur) {
        medecinService.supprimerMedecin(idUtilisateur);
        return ResponseEntity.ok("Medecin deleted successfully");
    }

    @GetMapping
    public ResponseEntity<Page<MedecinResDto>> listerMedecin(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(medecinService.listerMedecin(page, size));
    }

    @PutMapping("/{idUtilisateur}")
    public ResponseEntity<String> modifierMedecin(
            @PathVariable String idUtilisateur,
            @RequestBody MedecinReqDto medecinReqDto) {
        medecinService.modifierMedecin(idUtilisateur, medecinReqDto);
        return ResponseEntity.ok("Medecin updated successfully");
    }

    @GetMapping("/specialite/{specialite}")
    public ResponseEntity<Page<MedecinResDto>> rechercherParSpecialite(
            @PathVariable String specialite,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(medecinService.rechercherParSpecialite(specialite, page, size));
    }

    @GetMapping("/specialite/{specialite}/ville/{ville}")
    public ResponseEntity<Page<MedecinResDto>> rechercheSpecialisteVille(
            @PathVariable String specialite,
            @PathVariable String ville,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(medecinService.rechercheSpecialisteVille(specialite, ville, page, size));
    }
}
