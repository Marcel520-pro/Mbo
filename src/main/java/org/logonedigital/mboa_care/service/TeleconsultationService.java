package org.logonedigital.mboa_care.service;


import org.logonedigital.mboa_care.dto.TeleconsultationRequestDto;
import org.logonedigital.mboa_care.dto.TeleconsultationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeleconsultationService {

    TeleconsultationResponseDto create(TeleconsultationRequestDto dto);

    TeleconsultationResponseDto getById(String id);

    Page<TeleconsultationResponseDto> getQueue(Pageable pageable);

    void startNextConsultation();

    void finishConsultation(String id);

    void cancelConsultation(String id);

    void reschedule(String id, TeleconsultationRequestDto dto);
}