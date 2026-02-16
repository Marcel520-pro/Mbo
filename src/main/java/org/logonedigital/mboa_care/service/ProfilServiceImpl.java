package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.UtilisateurReqDto;
import org.logonedigital.mboa_care.dto.UtilisateurResDto;
import org.logonedigital.mboa_care.entity.Location;
import org.logonedigital.mboa_care.entity.Role;
import org.logonedigital.mboa_care.entity.Utilisateur;
import org.logonedigital.mboa_care.exception.ResourceExistException;
import org.logonedigital.mboa_care.exception.ResourceNotFoundException;
import org.logonedigital.mboa_care.repository.LocationRepo;
import org.logonedigital.mboa_care.repository.ProfilRepo;
import org.logonedigital.mboa_care.repository.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfilServiceImpl implements  ProfilService {
    private final ProfilRepo profilRepo;
    private final LocationRepo  locationRepo;

    public ProfilServiceImpl(ProfilRepo profilRepo, LocationRepo locationRepo, RoleRepo roleRepo) {
        this.profilRepo = profilRepo;
        this.locationRepo = locationRepo;
    }

    @Override
    public void ajouterPatient(UtilisateurReqDto utilisateurReqDto) {
        if (profilRepo.existsByEmail(utilisateurReqDto.getEmail()))
            throw new ResourceExistException("Email deja utilise");

        if (utilisateurReqDto.getLocation()== null)
            throw new IllegalArgumentException("Location obligatoire pour un patient");

        Location location = new Location();
        location.setVille(utilisateurReqDto.getLocation().getVille());
        location.setQuartier(utilisateurReqDto.getLocation().getQuartier());
        locationRepo.save(location);

        Utilisateur utilisateur = Utilisateur.builder()
                .nomUtilisateur()
                .email()
                .password()
                .telephone()
                .dateNaissance()
                .createdAt()
                .updatedAt()
                .role(Role.PATIENT)
                .location()
                .build();
    }
}
