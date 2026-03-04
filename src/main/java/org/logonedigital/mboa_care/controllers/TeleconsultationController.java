package org.logonedigital.mboa_care.controllers;

import lombok.RequiredArgsConstructor;
import org.logonedigital.mboa_care.dto.CompteRenduRequestDto;
import org.logonedigital.mboa_care.dto.CompteRenduResponseDto;
import org.logonedigital.mboa_care.dto.TeleconsultationRequestDto;
import org.logonedigital.mboa_care.dto.TeleconsultationResponseDto;
import org.logonedigital.mboa_care.service.CompteRenduService;
import org.logonedigital.mboa_care.service.TeleconsultationService;
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

    @PostMapping
    public ResponseEntity<TeleconsultationResponseDto> create(
            @RequestBody TeleconsultationRequestDto dto
    ) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/{id}")
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
    public ResponseEntity<Void> startNext() {
        service.startNextConsultation();
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/finish")
    public ResponseEntity<Void> finish(@PathVariable String id) {
        service.finishConsultation(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable String id) {
        service.cancelConsultation(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/reschedule")
    public ResponseEntity<Void> reschedule(
            @PathVariable String id,
            @RequestBody TeleconsultationRequestDto dto
    ) {
        service.reschedule(id, dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/compte-rendu")
    public ResponseEntity<CompteRenduResponseDto> createCompteRendu(
            @PathVariable String id,
            @RequestBody CompteRenduRequestDto dto
    ) {
        return ResponseEntity.ok(compteRenduService.create(id, dto));
    }

    @GetMapping("/{id}/compte-rendu")
    public ResponseEntity<CompteRenduResponseDto> getCompteRendu(@PathVariable String id) {
        return ResponseEntity.ok(compteRenduService.getByConsultation(id));
    }
}