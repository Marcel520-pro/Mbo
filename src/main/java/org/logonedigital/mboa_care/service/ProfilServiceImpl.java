package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.*;
import org.logonedigital.mboa_care.entity.*;
import org.logonedigital.mboa_care.exception.ResourceExistException;
import org.logonedigital.mboa_care.exception.ResourceNotFoundException;
import org.logonedigital.mboa_care.exception.RoleException;
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
        Utilisateur utilisateur =  profilRepo.findById(idUtilisateur)
                .orElseThrow(()-> new ResourceNotFoundException("Patient Introuvable !"));

        if (!(utilisateur instanceof Patient patient))
            throw new RoleException("L'utilisateur n'est pas un patient !");

        return PatientResDto.builder()
                .idUtilisateur(patient.getIdUtilisateur())
                .nomUtilisateur(patient.getNomUtilisateur())
                .email(patient.getEmail())
                .telephone(patient.getTelephone())
                .role(patient.getRole())
                .location(
                        LocationDto.builder()
                                .ville(patient.getLocation().getVille())
                                .quartier(patient.getLocation().getQuartier())
                                .build()
                )
                .build();
    }

    @Override
    public MedecinResDto consulterMedecin(String idUtilisateur) {
        Utilisateur utilisateur =  profilRepo.findById(idUtilisateur)
                .orElseThrow(()-> new ResourceNotFoundException("Medecin Introuvable !"));

        if (!(utilisateur instanceof Medecin medecin))
            throw new RoleException("L'utilisateur n'est pas un medecin");

        return MedecinResDto.builder()
                .idUtilisateur(medecin.getIdUtilisateur())
                .nomUtilisateur(medecin.getNomUtilisateur())
                .email(medecin.getEmail())
                .telephone(medecin.getTelephone())
                .role(medecin.getRole())
                .specialite(medecin.getSpecialite())
                .location(
                        LocationDto.builder()
                                .ville(medecin.getLocation().getVille())
                                .quartier(medecin.getLocation().getQuartier())
                                .build()
                )
                .build();
    }

    @Override
    public List<MedecinResDto> listerMedecin() {
        return profilRepo.findByRole(Role.MEDECIN)
                .stream()
                .map(utilisateur -> {
                    Medecin medecin = (Medecin) utilisateur;

                    return MedecinResDto.builder()
                            .idUtilisateur(medecin.getIdUtilisateur())
                            .nomUtilisateur(medecin.getNomUtilisateur())
                            .email(medecin.getEmail())
                            .telephone(medecin.getTelephone())
                            .role(medecin.getRole())
                            .specialite(medecin.getSpecialite())
                            .location(
                                    LocationDto.builder()
                                            .ville(medecin.getLocation().getVille())
                                            .quartier(medecin.getLocation().getQuartier())
                                            .build()
                            )
                            .build();
                }).toList();

    }

    @Override
    public List<PatientResDto> listerPatients() {
        return profilRepo.findByRole(Role.PATIENT)
                .stream()
                .map(utilisateur -> {
                    Patient patient = (Patient) utilisateur;

                    return PatientResDto.builder()
                            .idUtilisateur(patient.getIdUtilisateur())
                            .nomUtilisateur(patient.getNomUtilisateur())
                            .email(patient.getEmail())
                            .telephone(patient.getTelephone())
                            .role(patient.getRole())
                            .location(
                                    LocationDto.builder()
                                            .ville(patient.getLocation().getVille())
                                            .quartier(patient.getLocation().getQuartier())
                                            .build()
                            )
                            .build();
                })
                .toList();
    }

    @Override
    public void modifierPatient(String idUtilisateur, PatientReqDto patientReqDto) {

    }

    @Override
    public void modifierMedecin(String idUtilisateur, MedecinReqDto medecinReqDto) {

    }

    @Override
    public void supprimerProfil(String idUtilisateur) {
        profilRepo.deleteById(idUtilisateur);
    }
}
