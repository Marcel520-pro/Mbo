package org.logonedigital.mboa_care.controllers;

import lombok.RequiredArgsConstructor;
import org.logonedigital.mboa_care.dto.CompteRenduRequestDto;
import org.logonedigital.mboa_care.dto.CompteRenduResponseDto;
import org.logonedigital.mboa_care.dto.TeleconsultationRequestDto;
import org.logonedigital.mboa_care.dto.TeleconsultationResponseDto;
import org.logonedigital.mboa_care.service.CompteRenduService;
import org.logonedigital.mboa_care.service.TeleconsultationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/consultations")
@RequiredArgsConstructor
public class TeleconsultationController {

    private final TeleconsultationService service;
    private final CompteRenduService compteRenduService;

    @PostMapping("/create_consultation")
    public ResponseEntity<TeleconsultationResponseDto> create(
            @RequestBody TeleconsultationRequestDto dto
    ) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/get_consultation{idConsultation}")
    public ResponseEntity<TeleconsultationResponseDto> get(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/queue")
    public ResponseEntity<Page<TeleconsultationResponseDto>> getQueue(
            Pageable pageable
    ) {
        return ResponseEntity.ok(service.getQueue(pageable));
    }

    @PatchMapping("/queue/start")
    public ResponseEntity<String> startNext() {
        service.startNextConsultation();
        return ResponseEntity.status(200).body("Consultation commencer avec success");
    }

    @PatchMapping("/{id}/finish")
    public ResponseEntity<String> finish(@PathVariable String id) {
        service.finishConsultation(id);
        return ResponseEntity.status(200).body("Consultation terminee avec success");
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<String> cancel(@PathVariable String id) {
        service.cancelConsultation(id);
        return ResponseEntity.status(200).body("Consultation annulee avec success");
    }

    @PatchMapping("/{id}/reschedule")
    public ResponseEntity<String> reschedule(
            @PathVariable String id,
            @RequestBody TeleconsultationRequestDto dto
    ) {
        service.reschedule(id, dto);
        return ResponseEntity.status(200).body("Consultation reprogrammer avec success");
    }

    @PostMapping("/{id}/compte-rendu")
    public ResponseEntity<CompteRenduResponseDto> createCompteRendu(
            @PathVariable String id,
            @RequestBody CompteRenduRequestDto dto
    ) {
        return ResponseEntity.status(200).body(compteRenduService.create(id, dto));
    }

    @GetMapping("/{idCompteRendu}/get_compte-rendu")
    public ResponseEntity<CompteRenduResponseDto> getCompteRendu(@PathVariable String id) {
        return ResponseEntity.ok(compteRenduService.getByConsultation(id));
    }

    @Value("${spring.mail.username}")
    private String mailUser;

    @GetMapping("/check-mail-config")
    public String checkMail() {
        return mailUser;
    }
}