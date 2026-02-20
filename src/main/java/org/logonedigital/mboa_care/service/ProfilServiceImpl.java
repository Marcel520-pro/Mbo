package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.UtilisateurReqDto;
import org.logonedigital.mboa_care.entity.Location;
import org.logonedigital.mboa_care.entity.Patient;
import org.logonedigital.mboa_care.exception.ResourceExistException;
import org.logonedigital.mboa_care.repository.LocationRepo;
import org.logonedigital.mboa_care.repository.ProfilRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProfilServiceImpl implements  ProfilService {
    private final ProfilRepo profilRepo;
    private final LocationRepo  locationRepo;

    public ProfilServiceImpl(ProfilRepo profilRepo, LocationRepo locationRepo) {
        this.profilRepo = profilRepo;
        this.locationRepo = locationRepo;
    }

    @Override
    public void ajouterPatient(UtilisateurReqDto utilisateurReqDto) {
        if (profilRepo.existsByEmail(utilisateurReqDto.getEmail()))
            throw new ResourceExistException("Email déjà utilisé");

        if (utilisateurReqDto.getLocation() == null)
            throw new IllegalArgumentException("Location obligatoire pour un patient");

        Location location = Location.builder()
                .ville(utilisateurReqDto.getLocation().getVille())
                .quartier(utilisateurReqDto.getLocation().getQuartier())
                .build();
        locationRepo.save(location);

        Patient patient = new Patient(
                utilisateurReqDto.getNomUtilisateur(),
                utilisateurReqDto.getEmail(),
                utilisateurReqDto.getTelephone(),
                utilisateurReqDto.getMotDePasse(),
                location
        );
        patient.setCreatedAt(LocalDate.now());

        profilRepo.save(patient);

    }
}
