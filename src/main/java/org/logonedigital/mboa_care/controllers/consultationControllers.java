package org.logonedigital.mboa_care.controllers;

import jakarta.validation.Valid;
import org.logonedigital.mboa_care.dto.ConsultationReqDto;
import org.logonedigital.mboa_care.dto.ConsultationResDto;
import org.logonedigital.mboa_care.entity.Teleconsultation;
import org.logonedigital.mboa_care.service.ConsultationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ap1/v1/teleconsultation")
public class consultationControllers {
    private final ConsultationService consultationService;

    public consultationControllers(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @PostMapping(path = "/creer_consultation")
    public ResponseEntity<String> creerConsultation(@RequestBody @Valid ConsultationReqDto consultationReqDto) {
        this.consultationService.creerTeleconsultation(consultationReqDto);
        return ResponseEntity.status(201).body("Teleconsultation created");
    }

    @GetMapping(path = "/get_connsultation{idConsultation}")
    public ResponseEntity<ConsultationResDto>  getConsultation(@PathVariable("idConsultation") String idConsultation) {
        return ResponseEntity.status(201).body(this.consultationService.getById(idConsultation));
    }

    @GetMapping(path = "/get_all_consultation")
    public ResponseEntity<List<Teleconsultation>> getAllConsultations() {
        return ResponseEntity.status(201).body(this.consultationService.listerTeleconsultations());
    }

    @PatchMapping(path = "/commencer_consultation{idConsultation}")
    public ResponseEntity<String> commencerConsultation(@PathVariable("idConsultation") String idConsultation, String idMedecin) {
        this.consultationService.commencerConsultation(idConsultation, idMedecin);
        return ResponseEntity.status(201).body("Consultation commenced");
    }

    @PatchMapping(path = "/terminer_consultation{idConsultation}")
    public ResponseEntity<String> terminerConsultation(@PathVariable("idConsultation") String idConsultation,  String idMedecin) {
        this.consultationService.terminerConsultation(idConsultation, idMedecin);
        return ResponseEntity.status(201).body("Consultation terminated");
    }

    @DeleteMapping(path = "/supprimer_consultation{idConsultation}")
    public ResponseEntity<String> supprimerConsultation(@PathVariable("idConsultation") String idConsultation) {
        this.consultationService.supprimerTeleconsultation(idConsultation);
        return ResponseEntity.status(201).body("Consultation supprimed");
    }
}
