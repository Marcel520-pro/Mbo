package org.logonedigital.mboa_care.service;

import org.logonedigital.mboa_care.dto.UtilisateurReqDto;
import org.logonedigital.mboa_care.dto.UtilisateurResDto;
import org.logonedigital.mboa_care.repository.LocationRepo;
import org.logonedigital.mboa_care.repository.ProfilRepo;

import java.util.List;

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
        return null;
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
}
