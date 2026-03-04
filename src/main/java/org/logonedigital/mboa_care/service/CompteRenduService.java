package org.logonedigital.mboa_care.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.logonedigital.mboa_care.dto.CompteRenduRequestDto;
import org.logonedigital.mboa_care.dto.CompteRenduResponseDto;
import org.logonedigital.mboa_care.dto.TeleconsultationMapper;
import org.logonedigital.mboa_care.entity.CompteRendu;
import org.logonedigital.mboa_care.entity.StatutConsultation;
import org.logonedigital.mboa_care.entity.Teleconsultation;
import org.logonedigital.mboa_care.exception.ResourceNotFoundException;
import org.logonedigital.mboa_care.exception.UnauthorizedActionException;
import org.logonedigital.mboa_care.repository.CompteRenduRepository;
import org.logonedigital.mboa_care.repository.TeleconsultationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class CompteRenduService {

    private final CompteRenduRepository repository;
    private final TeleconsultationRepository consultationRepo;
    private final TeleconsultationMapper mapper;
    private final EmailService emailService;

    public CompteRenduResponseDto create(String consultationId, CompteRenduRequestDto dto) {
        Teleconsultation consultation = consultationRepo.findById(consultationId)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation introuvable"));

        if (consultation.getStatut() != StatutConsultation.TERMINEE)
            throw new UnauthorizedActionException("Le compte rendu ne peut être créé que pour une consultation TERMINEE");

        if (repository.findByConsultation(consultation).isPresent())
            throw new UnauthorizedActionException("Un compte rendu existe déjà pour cette consultation");

        CompteRendu report = CompteRendu.builder()
                .consultation(consultation)
                .contenu(dto.getContenu())
                .emailDestinataire(consultation.getEmailDestinataire())
                .dateRendu(LocalDateTime.now())
                .build();

        report = repository.save(report);

        // Send email
        emailService.sendReport(report.getEmailDestinataire(), report.getContenu());

        return mapper.toDto(report);
    }

    public CompteRenduResponseDto getByConsultation(String consultationId) {
        Teleconsultation consultation = consultationRepo.findById(consultationId)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation introuvable"));
        CompteRendu report = repository.findByConsultation(consultation)
                .orElseThrow(() -> new ResourceNotFoundException("Compte rendu introuvable"));
        return mapper.toDto(report);
    }
}