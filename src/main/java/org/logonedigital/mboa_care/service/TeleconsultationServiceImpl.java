package org.logonedigital.mboa_care.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.logonedigital.mboa_care.dto.TeleconsultationMapper;
import org.logonedigital.mboa_care.dto.TeleconsultationRequestDto;
import org.logonedigital.mboa_care.dto.TeleconsultationResponseDto;
import org.logonedigital.mboa_care.entity.StatutConsultation;
import org.logonedigital.mboa_care.entity.Teleconsultation;
import org.logonedigital.mboa_care.exception.ResourceNotFoundException;
import org.logonedigital.mboa_care.exception.UnauthorizedActionException;
import org.logonedigital.mboa_care.repository.TeleconsultationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class TeleconsultationServiceImpl implements TeleconsultationService {

    private final TeleconsultationRepository repository;
    private final TeleconsultationMapper mapper;

    @Override
    public TeleconsultationResponseDto create(TeleconsultationRequestDto dto) {
        Teleconsultation entity = mapper.toEntity(dto);
        entity.setDateCreation(LocalDateTime.now());
        entity.setStatut(StatutConsultation.PLANIFIEE);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public TeleconsultationResponseDto getById(String id) {
        Teleconsultation entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation introuvable"));
        return mapper.toDto(entity);
    }

    @Override
    public Page<TeleconsultationResponseDto> getQueue(Pageable pageable) {
        return repository.findByStatutOrderByDateCreationAsc(
                        StatutConsultation.PLANIFIEE, pageable)
                .map(mapper::toDto);
    }

    @Override
    public void startNextConsultation() {
        Teleconsultation next = repository.findFirstByStatutOrderByDateCreationAsc(
                        StatutConsultation.PLANIFIEE)
                .orElseThrow(() -> new ResourceNotFoundException("Aucune consultation en attente"));
        next.setStatut(StatutConsultation.EN_COURS);
        next.setDateDebut(LocalDateTime.now());
    }

    @Override
    public void finishConsultation(String id) {
        Teleconsultation consultation = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation introuvable"));
        if (consultation.getStatut() != StatutConsultation.EN_COURS)
            throw new UnauthorizedActionException("Seule une consultation EN_COURS peut être terminée");
        consultation.setStatut(StatutConsultation.TERMINEE);
        consultation.setDateFin(LocalDateTime.now());
    }

    @Override
    public void cancelConsultation(String id) {
        Teleconsultation consultation = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation introuvable"));
        if (consultation.getStatut() != StatutConsultation.PLANIFIEE)
            throw new UnauthorizedActionException("Seule une consultation PLANIFIEE peut être annulée");
        consultation.setStatut(StatutConsultation.ANNULEE);
    }

    @Override
    public void reschedule(String id, TeleconsultationRequestDto dto) {
        Teleconsultation consultation = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation introuvable"));
        if (consultation.getStatut() != StatutConsultation.PLANIFIEE)
            throw new UnauthorizedActionException("Seule une consultation PLANIFIEE peut être replanifiée");
        consultation.setDateConsultation(dto.getDateConsultation());
        consultation.setDateCreation(LocalDateTime.now()); // FIFO reposition
    }
}