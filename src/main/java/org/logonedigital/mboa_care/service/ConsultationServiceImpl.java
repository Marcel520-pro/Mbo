package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.ConsultationReqDto;
import org.logonedigital.mboa_care.dto.ConsultationResDto;
import org.logonedigital.mboa_care.entity.Statut;
import org.logonedigital.mboa_care.entity.Teleconsultation;
import org.logonedigital.mboa_care.entity.Utilisateur;
import org.logonedigital.mboa_care.exception.ResourceNotFoundException;
import org.logonedigital.mboa_care.exception.UnauthorizedActionException;
import org.logonedigital.mboa_care.repository.ConsultationRepo;
import org.logonedigital.mboa_care.repository.ProfilRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationServiceImpl implements  ConsultationService {
    private final ConsultationRepo consultationRepo;
    private final ProfilRepo profilRepo;

    public ConsultationServiceImpl(ConsultationRepo consultationRepo, ProfilRepo profilRepo) {
        this.consultationRepo = consultationRepo;
        this.profilRepo = profilRepo;
    }

    @Override
    public void creerTeleconsultation(ConsultationReqDto consultationReqDto) {
        Utilisateur patient = profilRepo.
                findById(consultationReqDto.getIdPatient()).
                orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        Utilisateur medecin = profilRepo.
                findById(consultationReqDto.getIdMedecin()).
                orElseThrow(() -> new ResourceNotFoundException("Medecin not found"));

//        if (patient.getRole() != Role.PATIENT)
//            throw new InvalidRoleException("Utilisateur n'est pas un patient");
//        if (patient.getRole() != Role.MEDECIN)
//            throw new InvalidRoleException("Utilisateur n'est pas un medecin");

        Teleconsultation teleconsultation = Teleconsultation.builder()
                .consultationDate(consultationReqDto.getDateConsultation())
                .motif(consultationReqDto.getMotif())
                .status(Statut.EN_ATTENTE)
                .patient(patient)
                .medecin(medecin)
                .build();

        consultationRepo.save(teleconsultation);
    }

    @Override
    public List<Teleconsultation> listerTeleconsultations() {
        return consultationRepo.findAll().stream()
                .map(tc -> Teleconsultation.builder()
                        .idConsultation(tc.getIdConsultation())
                        .consultationDate(tc.getConsultationDate())
                        .motif(tc.getMotif())
                        .patient(tc.getPatient())
                        .medecin(tc.getMedecin())
                        .build()
                )
                .toList();
    }

    @Override
    public ConsultationResDto getById(String idConsultation) {
        Teleconsultation teleconsultation = consultationRepo.findById(idConsultation)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found"));

        return ConsultationResDto.builder()
                .idConsultation(teleconsultation.getIdConsultation())
                .dateConsultation(teleconsultation.getConsultationDate())
                .motif(teleconsultation.getMotif())
                .statut(teleconsultation.getStatus().name())
                .nomPatient(teleconsultation.getPatient().getNomUtilisateur())
                .nomMedecin(teleconsultation.getMedecin().getNomUtilisateur())
                .build();
    }

    @Override
    public void commencerConsultation(String idConsultation, String idMedecin) {
        Teleconsultation teleconsultation = consultationRepo.findById(idConsultation)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found"));

        if (!teleconsultation.getMedecin().getIdUtilisateur().equals(idMedecin))
            throw new UnauthorizedActionException("Seul un medecin peut lancer une consultation");

        if (teleconsultation.getStatus() != Statut.EN_ATTENTE)
            throw new IllegalStateException("Consultation status incorrect");

        teleconsultation.setStatus(Statut.EN_COURS);
        consultationRepo.save(teleconsultation);
    }

    @Override
    public void terminerConsultation(String idConsultation, String idMedecin) {
        Teleconsultation teleconsultation = consultationRepo.findById(idConsultation)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found"));

        if (!teleconsultation.getMedecin().getIdUtilisateur().equals(idMedecin))
            throw new UnauthorizedActionException("Seul un medecin peut lancer une consultation");

        if (teleconsultation.getStatus() != Statut.EN_COURS)
            throw new IllegalStateException("Consultation status incorrect");

        teleconsultation.setStatus(Statut.TERMINEE);
        consultationRepo.save(teleconsultation);
    }

    @Override
    public void supprimerTeleconsultation(String idConsultation) {
        Teleconsultation teleconsultation = consultationRepo.findById(idConsultation)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found"));
        consultationRepo.delete(teleconsultation);
    }
}
