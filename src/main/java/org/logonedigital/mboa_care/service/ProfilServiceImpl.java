package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.ModificationResDto;
import org.logonedigital.mboa_care.dto.UtilisateurReqDto;
import org.logonedigital.mboa_care.dto.UtilisateurResDto;
import org.logonedigital.mboa_care.entity.Location;
import org.logonedigital.mboa_care.entity.Role;
import org.logonedigital.mboa_care.entity.Utilisateur;
import org.logonedigital.mboa_care.exception.ResourceExistException;
import org.logonedigital.mboa_care.exception.ResourceNotFoundException;
import org.logonedigital.mboa_care.repository.LocationRepo;
import org.logonedigital.mboa_care.repository.ProfilRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProfilServiceImpl implements  ProfilService {
    private final ProfilRepo profilRepo;
    private final LocationRepo  locationRepo;

    public ProfilServiceImpl(ProfilRepo profilRepo, LocationRepo locationRepo) {
        this.profilRepo = profilRepo;
        this.locationRepo = locationRepo;
    }
}
