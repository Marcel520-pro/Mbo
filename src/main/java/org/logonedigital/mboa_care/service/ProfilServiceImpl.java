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
//=====================
    //CREER PROFIL
//=====================
@Override
public UtilisateurReqDto creerProfil(UtilisateurReqDto utilisateurReqDto) {
    if (profilRepo.existsByEmail(utilisateurReqDto.getEmail()))
        throw new ResourceExistException("Email deja utiliser");

    Utilisateur utilisateur = Utilisateur.builder()
            .nomUtilisateur(utilisateurReqDto.getNomUtilisateur())
            .email(utilisateurReqDto.getEmail())
            .password(utilisateurReqDto.getMotDePasse())
            .telephone(utilisateurReqDto.getTelephone())
            .build();

    Role role = Role.valueOf(utilisateurReqDto.getRole().toUpperCase());
    utilisateur.setRole(role);

    if (role == Role.PATIENT){
        if (utilisateurReqDto.getLocation() == null)
            throw new ResourceNotFoundException(
                    "Localisation obligatoire pour un patient"
            );

        Location location = new Location();
        location.setVille(utilisateurReqDto.getLocation().getVille());
        location.setQuartier(utilisateurReqDto.getLocation().getQuartier());
        locationRepo.save(location);
        utilisateur.setLocation(location);
    }
    utilisateur.setCreatedAt(LocalDate.now());
    profilRepo.save(utilisateur);
    return UtilisateurReqDto.builder()
            .nomUtilisateur(utilisateur.getNomUtilisateur())
            .email(utilisateur.getEmail())
            .telephone(utilisateur.getTelephone())
            .role(utilisateur.getRole().name())
            .build();
}

    @Override
    public void modifierProfil(String idUtilisateur, UtilisateurReqDto utilisateurReqDto) {

    }

    // ===================
    // CONSULTER PROFIL
    // ===================
    @Override
    public UtilisateurResDto consulterProfil(String idUtilisateur) {
        Utilisateur utilisateur = profilRepo.findById(idUtilisateur)
                .orElseThrow(()-> new ResourceNotFoundException("Utilisateur introuvable"));
        return UtilisateurResDto.builder()
                .idUtilisateur(utilisateur.getIdUtilisateur())
                .nomUtilisateur(utilisateur.getNomUtilisateur())
                .email(utilisateur.getEmail())
                .telephone(utilisateur.getTelephone())
                .role(utilisateur.getRole().name())
                .ville(utilisateur.getLocation() != null
                        ? utilisateur.getLocation().getVille()
                        : null)
                .quartier(utilisateur.getLocation() != null
                        ? utilisateur.getLocation().getQuartier()
                        : null)
                .build();
    }


    @Override
    public List<UtilisateurResDto> listerProfil() {
        return List.of();
    }

    @Override
    public void supprimerProfil(String idUtilisateur) {

    }
}
