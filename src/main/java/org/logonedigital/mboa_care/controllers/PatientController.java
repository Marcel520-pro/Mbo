package org.logonedigital.mboa_care.controllers;

import org.logonedigital.mboa_care.dto.PatientReqDto;
import org.logonedigital.mboa_care.dto.PatientResDto;
import org.logonedigital.mboa_care.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("create_patient")
    public ResponseEntity<String> ajouterPatient(@RequestBody PatientReqDto patientReqDto) {
        patientService.ajouterPatient(patientReqDto);
        return ResponseEntity.status(200).body("Patient created successfully");
    }

    @GetMapping("/get_patient{idUtilisateur}")
    public ResponseEntity<PatientResDto> consulterPatient(@PathVariable String idUtilisateur) {
        return ResponseEntity.status(200).body(patientService.consulterPatient(idUtilisateur));
    }

    @GetMapping("/get_all_patients")
    public ResponseEntity<Page<PatientResDto>> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(200).body(patientService.getAllPatients(pageable));
    }

    @DeleteMapping("/delete_patient{idUtilisateur}")
    public ResponseEntity<String> supprimerPatient(@PathVariable String idUtilisateur) {
        patientService.supprimerPatient(idUtilisateur);
        return ResponseEntity.status(200).body("Patient deleted successfully");
    }

    @PutMapping("modify_patient{idUtilisateur}")
    public ResponseEntity<String> modifierPatient(@RequestBody PatientReqDto patientReqDto) {
        patientService.modifierPatient(patientReqDto);
        return ResponseEntity.status(200).body("Patient updated successfully");
    }
}
