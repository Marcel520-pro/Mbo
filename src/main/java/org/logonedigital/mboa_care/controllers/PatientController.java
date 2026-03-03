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

    @PostMapping
    public ResponseEntity<String> ajouterPatient(@RequestBody PatientReqDto patientReqDto) {
        patientService.ajouterPatient(patientReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Patient created successfully");
    }

    @GetMapping("/{idUtilisateur}")
    public ResponseEntity<PatientResDto> consulterPatient(@PathVariable String idUtilisateur) {
        return ResponseEntity.ok(patientService.consulterPatient(idUtilisateur));
    }

    @GetMapping
    public ResponseEntity<Page<PatientResDto>> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(patientService.getAllPatients(pageable));
    }

    @DeleteMapping("/{idUtilisateur}")
    public ResponseEntity<String> supprimerPatient(@PathVariable String idUtilisateur) {
        patientService.supprimerPatient(idUtilisateur);
        return ResponseEntity.ok("Patient deleted successfully");
    }

    @PutMapping
    public ResponseEntity<String> modifierPatient(@RequestBody PatientReqDto patientReqDto) {
        patientService.modifierPatient(patientReqDto);
        return ResponseEntity.ok("Patient updated successfully");
    }
}
