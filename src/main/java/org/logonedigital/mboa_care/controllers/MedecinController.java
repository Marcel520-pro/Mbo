package org.logonedigital.mboa_care.controllers;

import org.logonedigital.mboa_care.dto.MedecinReqDto;
import org.logonedigital.mboa_care.dto.MedecinResDto;
import org.logonedigital.mboa_care.dto.PatientReqDto;
import org.logonedigital.mboa_care.service.ProfilService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ap1/v1/profil-utilisateur")
public class MedecinController {
    private final ProfilService profilService;

    public MedecinController(ProfilService profilService) {
        this.profilService = profilService;
    }

    @PostMapping(path = "/create_medecin")
    ResponseEntity<String> createMedecin(@RequestBody MedecinReqDto medecinReqDto) {
        this.profilService.ajouterMedecin(medecinReqDto);
        return ResponseEntity.status(201).body("Medecin created successfully");
    }

    @GetMapping(path = "/get_medecin_by_id{idUtilisateur}")
    ResponseEntity<MedecinResDto> getMedecinById(@PathVariable("idUtilisateur") String idUtilisateur) {
        return ResponseEntity.status(200).body(this.profilService.consulterMedecin(idUtilisateur));
    }

    @GetMapping(path = "/liste_medecins")
    ResponseEntity<Page<MedecinResDto>> getAllMedecins(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.status(200).body(this.profilService.listerMedecin(page, size));
    }

    @DeleteMapping(path = "/delete_profile")
    ResponseEntity<String> deleteProfil(@RequestParam("idUtilisateur") String idUtilisateur) {
        this.profilService.supprimerProfil(idUtilisateur);
        return ResponseEntity.status(200).body("Profil has been deleted successfully");
    }



    @PatchMapping(path = "/modify_medecin{idUtilisateur}")
    ResponseEntity<String> modifyMedecin(@PathVariable String idUtilisateur,@RequestBody MedecinReqDto medecinReqDto) {
        this.profilService.modifierMedecin(idUtilisateur,medecinReqDto);
        return ResponseEntity.status(200).body("Medecin modification has been successful");
    }

}
