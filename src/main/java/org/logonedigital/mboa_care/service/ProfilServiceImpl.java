package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.UtilisateurReqDto;
import org.logonedigital.mboa_care.dto.UtilisateurResDto;
import org.logonedigital.mboa_care.entity.Location;
import org.logonedigital.mboa_care.entity.Patient;
import org.logonedigital.mboa_care.exception.ResourceExistException;
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

    @Override
    public void ajouterPatient(UtilisateurReqDto utilisateurReqDto) {
        if (profilRepo.existsByEmail(utilisateurReqDto.getEmail())) {
            throw new ResourceExistException("Email deja utilise.... !");
        }

        Location location = Location.builder()
                .ville(utilisateurReqDto.getLocation().getVille())
                .quartier(utilisateurReqDto.getLocation().getQuartier())
                .build();
    }

    @Override
    public void ajouterMedecin(UtilisateurReqDto utilisateurReqDto) {

    }

    @Override
    public UtilisateurResDto consulterProfil(String idUtilisateur) {
        return null;
    }

    @Override
    public List<UtilisateurResDto> listerProfil() {
        return List.of();
    }

    @Override
    public void modifierProfil(String idUtilisateur, UtilisateurReqDto utilisateurReqDto) {

    }

    @Override
    public void supprimerProfil(String idUtilisateur) {

    }

    @Override
    public List<UtilisateurResDto> getAllPatients() {
        return List.of();
    }

    @Override
    public List<UtilisateurResDto> getAllMedecins() {
        return List.of();
    }
}
