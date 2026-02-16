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
import org.logonedigital.mboa_care.repository.RoleRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProfilServiceImpl implements  ProfilService {
    private final ProfilRepo profilRepo;
    private final LocationRepo  locationRepo;
    private final RoleRepo roleRepo;

    public ProfilServiceImpl(ProfilRepo profilRepo, LocationRepo locationRepo, RoleRepo roleRepo) {
        this.profilRepo = profilRepo;
        this.locationRepo = locationRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public void ajouterPatient(UtilisateurReqDto utilisateurReqDto) {
        if (profilRepo.existsByEmail(utilisateurReqDto.getEmail()))
            throw new ResourceExistException("Email deja utiliser");

        Role role = this.roleRepo.findByRole("PATIENT")
                .orElseThrow(() -> new ResourceNotFoundException("Role PATIENT introuvable"));

        Utilisateur utilisateur = Utilisateur.builder()
                .nomUtilisateur(utilisateurReqDto.getNomUtilisateur())
                .email(utilisateurReqDto.getEmail())
                .password(utilisateurReqDto.getMotDePasse())
                .telephone(utilisateurReqDto.getTelephone())
                .role(role)
                .build();

        Location location = new Location();
        location.setVille(utilisateurReqDto.getLocation().getVille());
        location.setQuartier(utilisateurReqDto.getLocation().getQuartier());
        locationRepo.save(location);
        utilisateur.setLocation(location);

        profilRepo.save(utilisateur);
    }

    @Override
    public void ajouterMedecin(UtilisateurReqDto utilisateurReqDto) {

    }

    @Override
    public void modifierProfil(String idUtilisateur, UtilisateurReqDto utilisateurReqDto) {

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
