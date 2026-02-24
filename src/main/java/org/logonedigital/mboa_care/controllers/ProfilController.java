package org.logonedigital.mboa_care.controllers;

import org.logonedigital.mboa_care.dto.MedecinReqDto;
import org.logonedigital.mboa_care.dto.MedecinResDto;
import org.logonedigital.mboa_care.dto.PatientReqDto;
import org.logonedigital.mboa_care.dto.PatientResDto;
import org.logonedigital.mboa_care.service.ProfilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @GetMapping(path = "/get_all_medecins")
//    ResponseEntity<List<MedecinResDto>> getAllMedecins() {
//        return ResponseEntity.status(201).body(this.profilService.listerMedecin());
//    }
//
//    @GetMapping(path = "/get_all_patients")
//    ResponseEntity<List<PatientResDto>> getAllPatients() {
//        return ResponseEntity.status(200).body(this.profilService.listerPatients());
//    }

    @DeleteMapping(path = "/delete_profile")
    ResponseEntity<String> deleteProfil(@RequestParam("idUtilisateur") String idUtilisateur) {
        this.profilService.supprimerProfil(idUtilisateur);
        return ResponseEntity.status(200).body("Profil has been deleted successfully");
    }

    @PatchMapping(path = "/modify_patient{idUtilisateur}")
    ResponseEntity<String> modifyPatient(@PathVariable String idUtilisateur,@RequestBody PatientReqDto patientReqDto) {
        this.profilService.modifierPatient(idUtilisateur,patientReqDto);
        return ResponseEntity.status(200).body("Patient modification has been successful");
    }

    @PatchMapping(path = "/modify_medecin{idUtilisateur}")
    ResponseEntity<String> modifyMedecin(@PathVariable String idUtilisateur,@RequestBody MedecinReqDto medecinReqDto) {
        this.profilService.modifierMedecin(idUtilisateur,medecinReqDto);
        return ResponseEntity.status(200).body("Medecin modification has been successful");
    }

}
