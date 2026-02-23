package org.logonedigital.mboa_care.controllers;

import org.logonedigital.mboa_care.dto.MedecinReqDto;
import org.logonedigital.mboa_care.dto.MedecinResDto;
import org.logonedigital.mboa_care.dto.PatientReqDto;
import org.logonedigital.mboa_care.dto.PatientResDto;
import org.logonedigital.mboa_care.service.ProfilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ap1/v1/profil-utilisateur")
public class ProfilController {
    private final ProfilService profilService;

    public ProfilController(ProfilService profilService) {
        this.profilService = profilService;
    }

    @PostMapping(path = "/create_patient")
    ResponseEntity<String> createPatient(@RequestBody PatientReqDto patientReqDto) {
        this.profilService.ajouterPatient(patientReqDto);
        return ResponseEntity.status(201).body("Patient created successfully");
    }

    @PostMapping(path = "/create_medecin")
    ResponseEntity<String> createMedecin(@RequestBody MedecinReqDto medecinReqDto) {
        this.profilService.ajouterMedecin(medecinReqDto);
        return ResponseEntity.status(201).body("Medecin created successfully");
    }

    @GetMapping(path = "/get_patient_by_id{idUtilisateur}")
    ResponseEntity<PatientResDto> getPatientById(@PathVariable("idUtilisateur") String idUtilisateur) {
        return ResponseEntity.status(200).body(this.profilService.consulterPatient(idUtilisateur));
    }

    @GetMapping(path = "/get_medecin_by_id{idUtilisateur}")
    ResponseEntity<MedecinResDto> getMedecinById(@PathVariable("idUtilisateur") String idUtilisateur) {
        return ResponseEntity.status(200).body(this.profilService.consulterMedecin(idUtilisateur));
    }


}
