package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.MedecinReqDto;
import org.logonedigital.mboa_care.dto.MedecinResDto;
import org.logonedigital.mboa_care.dto.PatientReqDto;
import org.logonedigital.mboa_care.dto.PatientResDto;
import org.logonedigital.mboa_care.entity.Location;
import org.logonedigital.mboa_care.entity.Medecin;
import org.logonedigital.mboa_care.entity.Patient;
import org.logonedigital.mboa_care.exception.ResourceExistException;
import org.logonedigital.mboa_care.repository.ProfilRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProfilServiceImpl implements ProfilService {
    private final ProfilRepo profilRepo;

    public ProfilServiceImpl(ProfilRepo profilRepo) {
        this.profilRepo = profilRepo;
    }

    @Override
    public void ajouterPatient(PatientReqDto patientReqDto) {
        if (profilRepo.existsByEmail(patientReqDto.getEmail()))
            throw new ResourceExistException("L'email est déjà utilisé !");

        Location location = Location.builder()
                .ville(patientReqDto.getLocation().getVille())
                .quartier(patientReqDto.getLocation().getQuartier())
                .build();
        Patient patient = new Patient(
                patientReqDto.getNomUtilisateur(),
                patientReqDto.getTelephone(),
                patientReqDto.getEmail(),
                patientReqDto.getPassword(),
                location
        );
        patient.setCreatedAt(LocalDate.now());
        profilRepo.save(patient);
    }

    @Override
    public void ajouterMedecin(MedecinReqDto medecinReqDto) {
        if (profilRepo.existsByEmail(medecinReqDto.getEmail()))
            throw new ResourceExistException("L'email deja utilise !");

        Location location = Location.builder()
                .ville(medecinReqDto.getLocation().getVille())
                .quartier(medecinReqDto.getLocation().getQuartier())
                .build();

        Medecin medecin = new Medecin(
                medecinReqDto.getNomUtilisateur(),
                medecinReqDto.getEmail(),
                medecinReqDto.getTelephone(),
                medecinReqDto.getPassword(),
                medecinReqDto.getSpecialite(),
                location
        );
        medecin.setCreatedAt(LocalDate.now());
        profilRepo.save(medecin);
    }

    @Override
    public PatientResDto consulterPatient(String idUtilisateur) {
        return null;
    }

    @Override
    public MedecinResDto consulterMedecin(String idUtilisateur) {
        return null;
    }

    @Override
    public List<MedecinResDto> listerMedecin() {
        return List.of();
    }

    @Override
    public List<PatientResDto> listerPatients() {
        return List.of();
    }

    @Override
    public void modifierPatient(PatientReqDto patientReqDto) {

    }

    @Override
    public void modifierMedecin(MedecinReqDto medecinReqDto) {

    }

    @Override
    public void supprimerProfil(String idUtilisateur) {

    }
}
