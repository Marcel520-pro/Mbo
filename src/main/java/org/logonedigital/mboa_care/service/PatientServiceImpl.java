package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.PatientReqDto;
import org.logonedigital.mboa_care.dto.PatientResDto;
import org.logonedigital.mboa_care.dto.LocationDto;
import org.logonedigital.mboa_care.entity.Location;
import org.logonedigital.mboa_care.entity.Patient;
import org.logonedigital.mboa_care.exception.ResourceExistException;
import org.logonedigital.mboa_care.exception.ResourceNotFoundException;
import org.logonedigital.mboa_care.repository.PatientRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepo patientRepo;

    public PatientServiceImpl(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Override
    public void ajouterPatient(PatientReqDto patientReqDto) {
        if (patientRepo.existsByEmail(patientReqDto.getEmail())) {
            throw new ResourceExistException("email existente");
        }
        Location location = new Location();
        location.setVille(patientReqDto.getLocationDto().getVille());
        location.setQuartier(patientReqDto.getLocationDto().getQuartier());

        Patient patient = new Patient();
        patient.setNom(patientReqDto.getNom());
        patient.setEmail(patientReqDto.getEmail());
        patient.setTelephone(patientReqDto.getTelephone());
        patient.setGroupSanguin(patientReqDto.getGroupSanguin());
        patient.setAllergies(patientReqDto.getAllergies());
        patient.setAntecedents(patientReqDto.getAntecedents());
        patient.setLocation(location);
        patientRepo.save(patient);
    }

    @Override
    public PatientResDto consulterPatient(String idUtilisateur) {
        Patient patient = patientRepo.findById(idUtilisateur)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + idUtilisateur));
        return mapToResponseDto(patient);
    }

    @Override
    public Page<PatientResDto> getAllPatients(Pageable pageable) {
        Page<Patient> patients = patientRepo.findAll(pageable);
        List<PatientResDto> patientResDtos = patients.getContent()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
        return new PageImpl<>(patientResDtos, pageable, patients.getTotalElements());
    }

    @Override
    public void supprimerPatient(String idUtilisateur) {
        if (!patientRepo.existsById(idUtilisateur)) {
            throw new ResourceNotFoundException("Patient not found with ID: " + idUtilisateur);
        }
        patientRepo.deleteById(idUtilisateur);
    }

    @Override
    public void modifierPatient(PatientReqDto patientReqDto) {
        // Note: Assuming patientReqDto has an idUtilisateur or requires proper mapping
        // This requires modifying PatientReqDto to include idUtilisateur
        // For now, this is a placeholder implementation
        throw new UnsupportedOperationException("Modify operation not fully implemented");
    }

    private PatientResDto mapToResponseDto(Patient patient) {
        LocationDto locationDto = new LocationDto();
        if (patient.getLocation() != null) {
            locationDto.setVille(patient.getLocation().getVille());
            locationDto.setQuartier(patient.getLocation().getQuartier());
        }

        return PatientResDto.builder()
                .idUtilisateur(patient.getIdUtilisateur())
                .nom(patient.getNom())
                .email(patient.getEmail())
                .telephone(patient.getTelephone())
                .location(locationDto)
                .build();
    }
}