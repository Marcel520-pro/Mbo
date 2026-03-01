package org.logonedigital.mboa_care.controllers;

import org.logonedigital.mboa_care.dto.PatientReqDto;
import org.logonedigital.mboa_care.dto.PatientResDto;
import org.logonedigital.mboa_care.entity.DossierHistory;
import org.logonedigital.mboa_care.service.DossierService;
import org.logonedigital.mboa_care.service.ProfilService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ap1/v1/profil-utilisateur")
public class PatientController {
    private final ProfilService profilService;
    private final DossierService dossierService;

    public PatientController(ProfilService profilService, DossierService dossierService) {
        this.profilService = profilService;
        this.dossierService = dossierService;
    }

    @PostMapping(path = "/create_patient")
    ResponseEntity<String> createPatient(@RequestBody PatientReqDto patientReqDto) {
        this.profilService.ajouterPatient(patientReqDto);
        return ResponseEntity.status(201).body("Patient created successfully");
    }

    @GetMapping(path = "/get_patient_by_id{idUtilisateur}")
    ResponseEntity<PatientResDto> getPatientById(@PathVariable("idUtilisateur") String idUtilisateur) {
        return ResponseEntity.status(200).body(this.profilService.consulterPatient(idUtilisateur));
    }

    @GetMapping(path = "/liste_patients")
    ResponseEntity<Page<PatientResDto>> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.status(200).body(this.profilService.listerPatients(page, size));
    }

    @PatchMapping(path = "/modify_patient{idUtilisateur}")
    ResponseEntity<String> modifyPatient(@PathVariable String idUtilisateur,@RequestBody PatientReqDto patientReqDto) {
        this.profilService.modifierPatient(idUtilisateur,patientReqDto);
        return ResponseEntity.status(200).body("Patient modification has been successful");
    }

    /**
     * Télécharge le dossier médical d'un patient en PDF
     */
    @GetMapping("/{idPatient}/dossier/download")
    public ResponseEntity<?> downloadDossier(@PathVariable String idPatient) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            byte[] pdfBytes = dossierService.generateDossierPdf(idPatient, username);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"dossier_medical_" + idPatient + ".pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la génération du PDF: " + e.getMessage());
        }
    }

    /**
     * Récupère l'historique des dossiers médicaux d'un patient
     */
    @GetMapping("/{idPatient}/historique")
    public ResponseEntity<Page<DossierHistory>> getPatientHistory(
            @PathVariable String idPatient,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<DossierHistory> history = dossierService.getPatientDossierHistory(idPatient, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(history);
    }
}
